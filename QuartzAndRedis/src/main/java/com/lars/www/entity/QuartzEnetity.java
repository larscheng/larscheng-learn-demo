package com.lars.www.entity;

import org.quartz.ScheduleBuilder;

import java.util.Map;

/**
 * 描述:
 * quartz任务通用实体
 *
 * @author zhengql
 * @date 2019/7/29 15:20
 */
public class QuartzEnetity {

    /**
     * 任务唯一标识
     * 推荐UUID
     * 或者其他唯一字符串
     */
    private String jobUniqueName;

    /***
     * 任务所在的任务组
     * 任务类的ClassName
     */
    private String jobGroupName;

    /**
     * 任务所在任务组编号，枚举
     */
    private Integer jobGroupType;

    /**
     * 任务的实际业务topic类型
     */
    private String jobTopic;

    /***
     * 任务描述
     */
    private String jobDescription;

    /***
     * 延时任务时：延时时间ms
     */
    private long delayTimes;

    /***
     * 定时任务时：cron表达式
     */
    private String cronExpression;

    /***
     * 任务参数列表
     */
    private Map<Object,Object> map;

    /***
     * 自定义策略任务的调度策略：调用方自行实现
     */
    private ScheduleBuilder scheduleBuilder;


    public String getJobUniqueName() {
        return jobUniqueName;
    }

    public QuartzEnetity setJobUniqueName(String jobUniqueName) {
        this.jobUniqueName = jobUniqueName;
        return this;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public QuartzEnetity setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
        return this;
    }

    public Integer getJobGroupType() {
        return jobGroupType;
    }

    public QuartzEnetity setJobGroupType(Integer jobGroupType) {
        this.jobGroupType = jobGroupType;
        return this;
    }

    public String getJobTopic() {
        return jobTopic;
    }

    public QuartzEnetity setJobTopic(String jobTopic) {
        this.jobTopic = jobTopic;
        return this;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public QuartzEnetity setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
        return this;
    }

    public long getDelayTimes() {
        return delayTimes;
    }

    public QuartzEnetity setDelayTimes(long delayTimes) {
        this.delayTimes = delayTimes;
        return this;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public QuartzEnetity setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    public ScheduleBuilder getScheduleBuilder() {
        return scheduleBuilder;
    }

    public QuartzEnetity setScheduleBuilder(ScheduleBuilder scheduleBuilder) {
        this.scheduleBuilder = scheduleBuilder;
        return this;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public QuartzEnetity setMap(Map<Object, Object> map) {
        this.map = map;
        return this;
    }

    @Override
    public String toString() {
        return "QuartzEnetity{" +
                "jobUniqueName='" + jobUniqueName + '\'' +
                ", jobGroupName='" + jobGroupName + '\'' +
                ", jobGroupType=" + jobGroupType +
                ", jobTopic='" + jobTopic + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", delayTimes=" + delayTimes +
                ", cronExpression='" + cronExpression + '\'' +
                ", map=" + map +
                ", scheduleBuilder=" + scheduleBuilder +
                '}';
    }
}
