package com.lars.www.entity;

/**
 *
 * 延时任务消息封装
 * @author zhengql
 * @date 2018/12/10 10:21
 * @return
 */
public class DelayMessage {

    /**
     * 消息主题,执行时通过不同的topic区分不同的业务
     */
    private String topic;
    /**
     * 消息id，确保唯一
     */
    private String id;
    /**
     * 消息延迟时间，单位ms
     */
    private long delay;
    /**
     * 消息优先级(0优先级最高，数字增大，优先级递减)
     */
    private int priority;
    /**
     * 消息存活时间（pool方案时，在池中的存活时间，单位s,ZSet方案中无需配置）
     */
    private long ttl;
    /**
     * 消息体，对应业务内容（序列化后的对象，json字符串）
     */
    private String body;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 消息状态（延迟-0；待发送-1；已发送-2；发送失败-3）
     */
    private int status;

    public String getTopic() {
        return topic;
    }

    public DelayMessage setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getId() {
        return id;
    }

    public DelayMessage setId(String id) {
        this.id = id;
        return this;
    }

    public long getDelay() {
        return delay;
    }

    public DelayMessage setDelay(long delay) {
        this.delay = delay;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public DelayMessage setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public long getTtl() {
        return ttl;
    }

    public DelayMessage setTtl(long ttl) {
        this.ttl = ttl;
        return this;
    }

    public String getBody() {
        return body;
    }

    public DelayMessage setBody(String body) {
        this.body = body;
        return this;
    }

    public long getCreateTime() {
        return createTime;
    }

    public DelayMessage setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public DelayMessage setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "DelayMessage{" +
                "topic='" + topic + '\'' +
                ", id='" + id + '\'' +
                ", delay=" + delay +
                ", priority=" + priority +
                ", ttl=" + ttl +
                ", body='" + body + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
