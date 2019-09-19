package com.lars.www.config;

import com.lars.www.entity.DelayMessageRoute;
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
         * pool+zset+list方案
         */
//        DelayMessageRoute routeTest = new DelayMessageRoute(Constants.REDIS_MQ_QUEUE_TEST,Constants.REDIS_MQ_LIST_TEST);

        /*
         * zset+异步调用方案
         */
        DelayMessageRoute routeTest = new DelayMessageRoute(Constants.REDIS_MQ_QUEUE_TEST);
        DelayMessageRoute routeDelay = new DelayMessageRoute(Constants.REDIS_MQ_QUEUE_DELAY);
        DelayMessageRoute routeRiding = new DelayMessageRoute(Constants.REDIS_MQ_QUEUE_DELAY_RIDING);
        DelayMessageRoute routeOta = new DelayMessageRoute(Constants.REDIS_MQ_QUEUE_DELAY_OTA);
        DelayMessageRoute routeNotice = new DelayMessageRoute(Constants.REDIS_MQ_QUEUE_DELAY_NOTICE);

        // TODO:
        //此处可扩展多个路由器监听多个队列
        routeList.add(routeTest);
        routeList.add(routeDelay);
        routeList.add(routeRiding);
        routeList.add(routeOta);
        routeList.add(routeNotice);
        return routeList;
    }
}
