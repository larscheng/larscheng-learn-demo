package com.lars.www.entity;

import java.math.BigInteger;

public class JobAndTriggerVo extends PageVo{
	private String job_name;
	private String job_group;
	private String job_class_name;
	private String trigger_name;
	private String trigger_group;
	private BigInteger repeat_interval;
	private BigInteger times_triggered;
	private String cron_expression;
	private String time_zone_id;

	public String getJob_name() {
		return job_name;
	}

	public JobAndTriggerVo setJob_name(String job_name) {
		this.job_name = job_name;
		return this;
	}

	public String getJob_group() {
		return job_group;
	}

	public JobAndTriggerVo setJob_group(String job_group) {
		this.job_group = job_group;
		return this;
	}

	public String getJob_class_name() {
		return job_class_name;
	}

	public JobAndTriggerVo setJob_class_name(String job_class_name) {
		this.job_class_name = job_class_name;
		return this;
	}

	public String getTrigger_name() {
		return trigger_name;
	}

	public JobAndTriggerVo setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
		return this;
	}

	public String getTrigger_group() {
		return trigger_group;
	}

	public JobAndTriggerVo setTrigger_group(String trigger_group) {
		this.trigger_group = trigger_group;
		return this;
	}

	public BigInteger getRepeat_interval() {
		return repeat_interval;
	}

	public JobAndTriggerVo setRepeat_interval(BigInteger repeat_interval) {
		this.repeat_interval = repeat_interval;
		return this;
	}

	public BigInteger getTimes_triggered() {
		return times_triggered;
	}

	public JobAndTriggerVo setTimes_triggered(BigInteger times_triggered) {
		this.times_triggered = times_triggered;
		return this;
	}

	public String getCron_expression() {
		return cron_expression;
	}

	public JobAndTriggerVo setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
		return this;
	}

	public String getTime_zone_id() {
		return time_zone_id;
	}

	public JobAndTriggerVo setTime_zone_id(String time_zone_id) {
		this.time_zone_id = time_zone_id;
		return this;
	}
}
