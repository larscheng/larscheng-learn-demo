package com.delay.mq.redisdelay.domain;

import com.alibaba.fastjson.JSONObject;
import com.delay.mq.redisdelay.utils.RedisMQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 延时消息任务执行
 * @author zhengql
 * @date 2018/12/10 11:10
 */
@Component
public class DelayMessageTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource private RedisMQ redisMQ;

    @Scheduled(cron="*/1 * * * * *")
    public void sendMsg() {
        // 消费
        List<String> msgList = redisMQ.consume(redisMQ.getRoutes().get(0).getList());
        int len;
        if (!CollectionUtils.isEmpty(msgList) && 0 < (len = msgList.size())) {
            // 将每一条消息转为JSONObject
            JSONObject jObj;
            for (int i = 0; i < len; i++) {
                if (!StringUtils.isEmpty(msgList.get(i))) {
                    // TODO:
                    // 取出消息，业务代码
                    jObj = JSONObject.parseObject(msgList.get(i));
                    System.out.println(jObj.toJSONString());
                    logger.info("redisMQ---->list: [{}] ---->延时消息执行: [{}]",redisMQ.getRoutes().get(0).getList(),jObj.toJSONString());
                }else {
                    logger.error("redisMQ---->list: [{}] ---->待消费列表中index: [{}] 的值为空",redisMQ.getRoutes().get(0).getList(),i);
                }
            }
        }
    }
}
