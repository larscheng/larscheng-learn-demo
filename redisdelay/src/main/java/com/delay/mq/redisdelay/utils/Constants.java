package com.delay.mq.redisdelay.utils;


/**
 * @author zhengql
 */
public class Constants {

    public static String CACHE_KEY_PREFIX = "reids_";



    //region redisMq
    /**
     * 消息池前缀，以此前缀加上传递的消息id作为key，以消息DelayMessage
     * 的消息体body作为值存储
     */
    public static final String MSG_POOL = "Message:Pool:";
    /**
     * 每次监听延时队列中的消息的数量
     */
    public static final Integer MONITOR_COUNT = 20;
    /***
     * 延时任务队列test
     */
    public static final String REDIS_MQ_QUEUE_TEST = "queue:test";
    /***
     * 延时任务队列delay
     */
    public static final String REDIS_MQ_QUEUE_DELAY = "queue:delay";


    /***
     * 待消费list
     */
    public static final String REDIS_MQ_LIST_TEST = "list:test";

    /***
     * 测试使用的消息topic
     */
    public static final String REDIS_MQ_TOPIC_TEST = "test";



    //endregion

}
