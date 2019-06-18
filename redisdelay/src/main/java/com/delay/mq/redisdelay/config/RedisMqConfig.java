package com.delay.mq.redisdelay.config;


import com.delay.mq.redisdelay.domain.DelayMessageRoute;
import com.delay.mq.redisdelay.utils.Constants;
import com.delay.mq.redisdelay.utils.RedisMQ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 消息队列配置
 *
 * @author zhengql
 * @date 2018/12/10 10:49
 */
@Configuration
public class RedisMqConfig {

    @Bean(name = "redisMQ")
    @Primary
    public RedisMQ getRedisMq() {
        RedisMQ redisMQ = new RedisMQ();
        // 配置监听队列元素数量
        redisMQ.setMonitorCount(Constants.MONITOR_COUNT);
        // 配置路由表
        redisMQ.setRoutes(routeList());
        return redisMQ;
    }

    /**
     * 返回路由表
     *
     * @return
     */
    public List<DelayMessageRoute> routeList() {
        List<DelayMessageRoute> routeList = new ArrayList<>();
        /*
         * zset+异步调用方案
         */
        DelayMessageRoute routeTest = new DelayMessageRoute(Constants.REDIS_MQ_QUEUE_TEST);
        DelayMessageRoute routeDelay = new DelayMessageRoute(Constants.REDIS_MQ_QUEUE_DELAY);

        // TODO:此处可扩展多个路由器监听多个队列
        routeList.add(routeTest);
        routeList.add(routeDelay);
        return routeList;
    }
}
