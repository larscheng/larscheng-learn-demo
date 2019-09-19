package com.lars.www.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lars.www.config.Constants;
import com.lars.www.config.RedisMQ;
import com.lars.www.entity.DelayMessage;
import com.lars.www.entity.DelayMessageVo;
import com.lars.www.enums.EnumDelayMqState;
import com.lars.www.response.Message;
import com.lars.www.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/1/27 14:05
 */
@Service
public class RedisMqService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisMQ redisMQ;

    private DelayMessage createMsg(Long delay) {
        JSONObject jObj = new JSONObject();
        String seqId = UUID.randomUUID().toString();
        jObj.put("msg", "这是一条短信"+delay);
        jObj.put("seqId", seqId);
        // 将有效信息放入消息队列和消息池中
        DelayMessage message = new DelayMessage();
        message.setBody(jObj.toJSONString());
        // 可以添加延迟配置
        message.setDelay(delay);//延迟时间ms
        message.setTopic("topic");//主题
        message.setCreateTime(System.currentTimeMillis());//创建时间
        message.setId(seqId);//消息id
        message.setTtl((delay / 1000) + 10);//在消息池中存活时间（稍大于延迟时间，路由器监控需要时间）单位s
        message.setStatus(0);
        message.setPriority(0);//优先级
        return message;
    }


    /***
     * 添加测试延时任务
     * @param delay 延迟时间ms
     * @return
     */
    public Message addTask(Long delay) {
        DelayMessage message = createMsg(delay);
        Long score = message.getCreateTime() + message.getDelay() + message.getPriority();
        //加入redis消息队列
//        System.out.println(JSONArray.toJSON(message).toString());
        boolean b = redisUtil.zSetAdd(Constants.REDIS_MQ_QUEUE_TEST, JSONArray.toJSON(message).toString(), score);
//        logger.info("{}---redisMQ---->queue: [{}] ---->加入延时消息id: [{}],延时时间:[{}]", b, Constants.REDIS_MQ_QUEUE_TEST, message.getId(), message.getDelay());
        return b ? Message.getSuccess() : Message.getError();
    }


    /***
     * 根据队列名称分页查询队列中的延时任务
     * @param vo
     * @return
     */
    public Message listTask(DelayMessageVo vo) {
        Map<String, Object> map = new HashMap<>(4);
        Integer total = redisUtil.zCard(vo.getQueue()).intValue();
        map.put("total", total);
        map.put("current", vo.getPage() );
        map.put("pages", total % vo.getPageSize() == 0 ? total / vo.getPageSize() : total / vo.getPageSize() + 1);
        List<DelayMessage> delayMessages = new ArrayList<>();

        Set<ZSetOperations.TypedTuple> string1Set = redisUtil.getRangeByScoreWithScores(vo.getQueue(), (vo.getPage()-1) * vo.getPageSize(), vo.getPage() * vo.getPageSize()-1,false);
        string1Set.forEach(item->{
            DelayMessage delayMessage = JSON.parseObject(item.getValue().toString(), new TypeReference<DelayMessage>() {
            });
            if (System.currentTimeMillis() > item.getScore()) {
                delayMessage.setStatus(3);
            }
            delayMessages.add(delayMessage);
        });
        map.put("record", delayMessages);
        map.put("EnumDelayMqState", EnumDelayMqState.toMap());
        return new Message(Message.CODE_SUCCESS, map);
    }


    /***
     * 每个zSet中任务数统计
     * @return
     */
    public Message countTask() {
        redisUtil.zSetAdd("zql:zzz","aaa",10000L);
        redisUtil.zSetAdd("zql:zzz","aaaa",20000L);
        System.out.println(redisUtil.zSetDelByScore("zql:zzz",10000L,10000L));

        Map<String, Object> map = new HashMap<>(20);
        redisMQ.getRoutes().forEach(a->{
            map.put(a.getQueue(), redisUtil.zCard(a.getQueue()));
        });
        return new Message(Message.CODE_SUCCESS, map);
    }

    /***
     * 删除任务
     * @param message
     * @return
     */
    public Message delTask(DelayMessageVo message) {
        DelayMessage delayMessage = new DelayMessage();
        BeanUtils.copyProperties(message,delayMessage);
        delayMessage.setStatus(0);
        boolean boo = redisUtil.zSetDel(message.getQueue(), JSONArray.toJSON(delayMessage).toString());
        logger.info("{}---redisMQ---->queue: [{}] ---->删除延时消息id: [{}],延时时间:[{}]", boo, message.getQueue(), message.getId(), message.getDelay());
        return boo ? Message.getSuccess() : Message.getError();
    }

    /***
     * 更新任务(两种方案)
     * 先删除在添加:key变化的情况(本方法)
     * 直接zAdd():key不变的情况下
     * @param messageList
     * @return
     */
    public Message updateJob(List<DelayMessageVo> messageList) {
        List<DelayMessage> delayMessages = new ArrayList<>();
        BeanUtils.copyProperties(messageList,delayMessages);
        boolean boo = redisUtil.zSetDel(messageList.get(0).getQueue(), JSONArray.toJSON(delayMessages.get(0)).toString());
        if (boo){
            logger.info("{}---redisMQ---->queue: [{}] ---->删除延时消息id: [{}],延时时间:[{}]", boo, messageList.get(1).getQueue(), messageList.get(0).getId(), messageList.get(0).getDelay());
            DelayMessage message = delayMessages.get(1);
            Long score = message.getCreateTime() + message.getDelay() + message.getPriority();
            //加入redis消息队列
            boolean b = redisUtil.zSetAdd(messageList.get(1).getQueue(), JSONArray.toJSON(message).toString(), score);
            logger.info("{}---redisMQ---->queue: [{}] ---->加入延时消息id: [{}],延时时间:[{}]", b, messageList.get(1).getQueue(), message.getId(), message.getDelay());
            return b ? Message.getSuccess() : Message.getError();
        }
        logger.info("{}---redisMQ---->queue: [{}] ---->删除延时消息id: [{}],删除失败！！！", boo, messageList.get(1).getQueue(), messageList.get(0).getId(), messageList.get(0).getDelay());
        return Message.getError();
    }


    /***
     * 指定队列添加任务
     * @param vo
     * @return
     */
    public Message addJob(DelayMessageVo vo) {
        DelayMessage message = new DelayMessage();
        BeanUtils.copyProperties(vo,message);
        String queue = vo.getQueue();
        Long score = message.getCreateTime() + message.getDelay() + message.getPriority();
        //加入redis消息队列
        boolean b = redisUtil.zSetAdd(queue, JSONArray.toJSON(message).toString(), score);
        logger.info("{}---redisMQ---->queue: [{}] ---->加入延时消息id: [{}],延时时间:[{}]", b, queue, message.getId(), message.getDelay());
        return b ? Message.getSuccess() : Message.getError();
    }



}
