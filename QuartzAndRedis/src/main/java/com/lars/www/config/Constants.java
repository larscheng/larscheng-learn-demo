package com.lars.www.config;

/**
 * Created by Jason on 2016/11/7.
 */
public class Constants {

    public static String CACHE_KEY_PREFIX = "reids_";

    /**盐*/
    public static final String SALT = "Senthink2017";
    //region 缓存
    public static final String EHCACHE_WEB_SOCKET = "WEB_SOCKET";
    //endregion

    /**
     * 一天的毫秒数
     */
    public static final Long ONE_DAY_TIME_MILLIS = 86400000L;




    /** 骑行数据 redis key 前缀 */
    public static final String RIDING_PREFIX = "RIDING_";

    /** 用户骑行数据 redis key 前缀 */
    public static final String USER_RIDING_PREFIX = "RIDING_USER_";





    //region 交换机、队列配置
    //MQ直连交换机
    public static final String D_E_SINGAPORE_LEASE = "d.e.singapore.lease";
    //任务队列
    public static final String Q_SINGAPORE_LEASE_SCHEDULE = "q.singapore.lease.schedule";
    //延时队列，车辆超过30s未开锁，自动关闭订单
    public static final String Q_D_L_SINGAPORE_LEASE_SCHEDULE1 = "q.d.l.singapore.lease.schedule1";
    //延时队列，订单超过24小时未结束
    public static final String Q_D_L_SINGAPORE_LEASE_SCHEDULE2 = "q.d.l.singapore.lease.schedule2";
    //延时队列，用户临时停车达到10min未重新解锁或还车
    public static final String Q_D_L_SINGAPORE_LEASE_SCHEDULE3 = "q.d.l.singapore.lease.schedule3";
    //延时队列
    public static final String Q_D_L_SINGAPORE_LEASE_SCHEDULE4 = "q.d.l.singapore.lease.schedule4";
    /** 骑行超时提醒队列*/
    public static final String Q_D_L_SINGAPORE_LEASE_RIDING_OVER_TIME = "q.d.l.singapore.lease.riding_over_time";
    //endregion

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
    public static final String REDIS_MQ_QUEUE_TEST = "redisMq:queue:test";
    /***
     * 延时任务队列delay
     */
    public static final String REDIS_MQ_QUEUE_DELAY = "redisMq:queue:delay";

    /***
     * 骑行时间提醒延时任务队列delay
     */
    public static final String REDIS_MQ_QUEUE_DELAY_RIDING= "redisMq:queue:delay:riding";

    /***
     * ota推送延时任务队列delay
     */
    public static final String REDIS_MQ_QUEUE_DELAY_OTA= "redisMq:queue:delay:ota";
    /***
     * 公告推送延时任务队列delay
     */
    public static final String REDIS_MQ_QUEUE_DELAY_NOTICE= "redisMq:queue:delay:notice";
    /***
     * 待消费list
     */
    public static final String REDIS_MQ_LIST_TEST = "list:test";

    /***
     * 测试使用的消息topic
     */
    public static final String REDIS_MQ_TOPIC_TEST = "test";




}
