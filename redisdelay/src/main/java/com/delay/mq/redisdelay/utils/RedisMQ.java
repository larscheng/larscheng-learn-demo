package com.delay.mq.redisdelay.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.delay.mq.redisdelay.async.AsyncTaskExecutor;
import com.delay.mq.redisdelay.domain.DelayMessage;
import com.delay.mq.redisdelay.domain.DelayMessageRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author zhengql
 * @decription RedisMQ
 * 基于redis的消息队列（两种实现）
 * 1:zSet+异步调用【推荐：简单】
 * 以zSet为消息队列,以任务的执行时间的时间戳(ms)作为score，存入zset
 * 线程池调度获取zset中score小于等于当前时间戳(ms)的待消费任务集合
 * 异步调用消费任务集合
 * <p>
 * 2:pool+zSet+list
 * 将整个redis作为消息池存储消息体，以ZSET为消息队列，LIST作为待消费列表
 * 用Spring定时器作为监听器，每次监听ZSET中指定数量的消息
 * 根据SCORE确定是否达到发送要求，如果达到，利用消息路由{@link DelayMessageRoute}将消息路由到待消费list
 * @date 2018/12/10 11:04
 */
public class RedisMQ {

    private Logger logger = LoggerFactory.getLogger(RedisMQ.class);



    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;



    /**
     * 消息路由
     */
    private List<DelayMessageRoute> routes;

    public List<DelayMessageRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<DelayMessageRoute> routes) {
        this.routes = routes;
    }




    /**
     * 消息队列监听器(zSet+异步方案)-----线程池调度
     * 监听所有路由器，将消息队列中的符合执行条件的消息路由到具体的消费者（业务执行方法）
     * 注:此实现未用到list，DelayMessageRoute对象只需要配置具体的queue即可(1个队列或多个)
     */
    public void consumerDelayMessage() {
        while (true) {
            int routeSize;
            String queue;
            if (null == routes || 1 > (routeSize = routes.size())) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            //可配置多个队列，多个队列则需要全部监听
            for (int i = 0; i < routeSize; i++) {
                queue = routes.get(i).getQueue();
                Long currentTime = System.currentTimeMillis();
                //获取集合中score小于或等于当前时间的结果集，并且score从小到大排列
                Set<ZSetOperations.TypedTuple> items = redisUtil.rangeByScoreWithScores(queue, 0, currentTime);
                if (items == null || items.isEmpty()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                for (ZSetOperations.TypedTuple item : items) {
                    String value = (String) item.getValue();
                    if (currentTime >= item.getScore()) {
                        DelayMessage message = JSON.parseObject(value, new TypeReference<DelayMessage>() {
                        });
                        if (redisUtil.zSetDel(queue, value)) {

                            //异步执行具体业务
                            asyncTaskExecutor.executedDelayTask(message);
                            logger.info("redisMq---->queue:[{}],清除队列中延时任务:[{}], 进入待消费状态", queue, message.toString());
                        }else {
                            logger.info("redisMq---->queue:[{}],清除队列中延时任务:[{}], 操作失败", queue, message.toString());
                            logger.info("redisMq---->queue:[{}],清除队列中延时任务:[{}], 操作失败!!!!", queue, value);
                        }
                    }
                }
            }

        }
    }




//-----------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------pool+zSet+list方案----------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * 默认监听数量，对应监听zset队列前多少个元素
     */
    private static final int DEFAULT_MONITOR = 10;
    /**
     * 每次监听queue中元素的数量，可配置
     */
    private int monitorCount = DEFAULT_MONITOR;


    public int getMonitorCount() {
        return monitorCount;
    }

    public void setMonitorCount(int monitorCount) {
        this.monitorCount = monitorCount;
    }

    /**
     * 存入消息池
     *
     * @param message
     * @return
     */
    public boolean addMsgPool(DelayMessage message) {

        if (null != message) {
            return redisUtil.set(Constants.MSG_POOL + message.getId(), message.getBody(), message.getTtl());
        }
        return false;
    }

    /**
     * 从消息池中删除消息
     *
     * @param id
     * @return
     */
    public boolean deMsgPool(String id) {

        return redisUtil.remove(Constants.MSG_POOL + id);
    }

    /**
     * 像队列中添加消息
     *
     * @param key
     * @param score
     * @param val
     * @return 返回消息id
     */
    public String enMessage(String key, long score, String val) {

        if (redisUtil.zSetAdd(key, val, score)) {
            return val;
        }
        return "";
    }

    /**
     * 从队列删除消息
     *
     * @param id
     * @return
     */
    public boolean deMessage(String key, String id) {

        return redisUtil.zSetDel(key, id);
    }


    /**
     * 消息队列监听器(pool+zSet+list方案)-----定时器调度
     * 监听所有路由器，将消息队列中的消息路由到待消费list列表,消费者再从具体的list列表中消费任务
     * 注：DelayMessageRoute对象必须配置对应的queue和list(1个或多个)
     */
//    @Scheduled(cron = "*/1 * * * * *")
    public void monitor() {
        // 获取消息路由
        int route_size;
        if (null == routes || 1 > (route_size = routes.size())) {
            return;
        }
        String queue, list;
        Set<String> set;
        for (int i = 0; i < route_size; i++) {
            queue = routes.get(i).getQueue();
            list = routes.get(i).getList();
            set = redisUtil.getRangeByScore(queue, 0, monitorCount, false);
            if (!CollectionUtils.isEmpty(set)) {
                long current = System.currentTimeMillis();
                long score;
                for (String id : set) {
                    score = redisUtil.getScore(queue, id).longValue();
                    if (current >= score) {
                        // 添加到list
                        if (redisUtil.rightPush(list, id)) {
                            // 删除queue中的元素
                            deMessage(queue, id);
                            logger.info("redisMq---->queue:[{}]---->list:[{}],清除队列中延时任务:[{}]，放入待消费list", queue, list, id);
                        } else {
                            logger.error("redisMq---->queue:[{}]---->list:[{}],清除队列中延时任务:[{}] 发生异常", queue, list, id);
                        }
                    }
                }
            }
        }
    }



    /**
     * 消息队列监听器(pool+queue+list方案)-----定时任务从lsit获取待消费的消息
     * @author zhengql
     * @date 2018/12/11 19:46
     */
//    @Scheduled(cron="*/1 * * * * *")
    public void sendMsg() {
        // 获取待消费集合
        List<String> msgs = this.consume(getRoutes().get(0).getList());
        int len;
        if (null != msgs && 0 < (len = msgs.size())) {
            // 将每一条消息转为JSONObject
            JSONObject jObj;
            for (int i = 0; i < len; i++) {
                if (!StringUtils.isEmpty(msgs.get(i))) {
                    jObj = JSONObject.parseObject(msgs.get(i));
                    // 取出消息
                    System.out.println("------------"+jObj.toJSONString());
                }
            }
        }
    }


    /**
     * 消息队列监听器(pool+queue+list方案)-----定时器调度
     * queue结合list的方案中，消费者从list中获取待消费的任务
     * 获取成功后清除list记录并返回
     * @param key
     * @return java.util.List<java.lang.String>
     * @author zhengql
     * @date 2018/12/11 19:19
     */
    public List<String> consume(String key) {

        long count = redisUtil.countList(key);
        if (0 < count) {
            // 可根据需求做限制
            List<String> ids = redisUtil.range(key, 0, count - 1);
            if (!CollectionUtils.isEmpty(ids)) {
                List<String> result = new ArrayList<>();
                for (String id : ids) {
                    String str = redisUtil.getValue(Constants.MSG_POOL + id, "");
                    if (!"".equals(str)) {
                        continue;
                    }
                    result.add(str);
                }
                logger.info("redisMQ---->list: [{}] ---->返回待消费的任务集合大小为: [{}]", key, ids.size());
                redisUtil.removeListValue(key, ids);
                return result;
            }
        }

        return null;
    }
}
