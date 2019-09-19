package com.lars.www.entity;

/**
 *
 * 消息路由器，主要控制将消息从指定的队列路由到待消费的list
 * 通过这种方式实现自定义延迟以及优先级发送
 * @author zhengql
 * @date 2018/12/10 10:24
 * @return
 */
public class DelayMessageRoute {

    /**
     * 存放消息的队列
     */
    private String queue;

    /**
     * 待消费的列表
     */
    private String list;

    public DelayMessageRoute(String queue, String list) {
        this.queue = queue;
        this.list = list;
    }
    public DelayMessageRoute(String queue) {
        this.queue = queue;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
