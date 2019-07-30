package com.delay.mq.redisdelay.async;

import com.alibaba.fastjson.JSONArray;
import com.delay.mq.redisdelay.utils.Constants;
import com.delay.mq.redisdelay.utils.RedisUtil;
import com.delay.mq.redisdelay.domain.DelayMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @version v0.0.1
 * @Description 异步
 * @Author
 * @Creation Date 2017年03月14日 上午11:34
 * @ModificationHistory Who When What -------- ----------
 * -----------------------------------
 */
@Component
public class AsyncTaskExecutor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("redisMqExecutor")
    private TaskExecutor taskExecutor;


    @Autowired
    private RedisUtil redisUtil;





    /***
     * 异步添加延时任务
     * @author zhengql
     * @date 2018/12/20 9:23
     * @param message
     * @return void
     */
    public void addDelayTask(DelayMessage message) {
        taskExecutor.execute(() -> {
            Long score = message.getCreateTime() + message.getDelay() + message.getPriority();
            //加入redis消息队列
            redisUtil.zSetAdd(Constants.REDIS_MQ_QUEUE_DELAY, JSONArray.toJSON(message).toString(), score);
            logger.info("redisMQ---->queue: [{}] ---->加入延时消息id: [{}],延时时间:[{}]", Constants.REDIS_MQ_QUEUE_DELAY, message.getId(), message.getDelay());
        });
    }


    /**
     * 异步执行redis延时任务
     *
     * @param message
     * @return void
     * @author zhengql
     * @date 2018/12/20 9:06
     */
    public void executedDelayTask(DelayMessage message) {
        taskExecutor.execute(() -> {
            if (ObjectUtils.isEmpty(message)) {
                return;
            }
            String topic = message.getTopic();
            switch (topic) {
                case Constants.REDIS_MQ_TOPIC_TEST:
                    //具体的业务
                    System.out.println("异步消费......" + message.getBody());
                    break;

                //TODO 其他topic业务
                case "ridingOverTime":
                    //具体的业务

                    System.out.println("异步消费......" + message.getBody());
                    break;
                case "test2":
                    //具体的业务
                    System.out.println("异步消费......" + message.getBody());
                    break;
                case "topic":
                    //具体的业务
                    System.out.println("异步消费......" + message.getBody());
                    break;

                default:
                    logger.warn("redisMQ---->延时消息id: [{}]消费异常----->无效的topic: [{}]", message.getId(), topic);
                    break;
            }
        });
    }



}
