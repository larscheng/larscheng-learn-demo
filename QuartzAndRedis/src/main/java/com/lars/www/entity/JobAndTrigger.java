package com.lars.www.entity;

import java.math.BigInteger;

public class JobAndTrigger {
	private String job_name;
	private String job_group;
	private String job_class_name;
	private String trigger_name;
	private String trigger_group;
	private BigInteger repeat_interval;
	private BigInteger times_triggered;
	private String cron_expression;
	private String time_zone_id;

	private String trigger_type;
	private String trigger_state;

	public String getJob_name() {
		return job_name;
	}

	public JobAndTrigger setJob_name(String job_name) {
		this.job_name = job_name;
		return this;
	}

	public String getJob_group() {
		return job_group;
	}

	public JobAndTrigger setJob_group(String job_group) {
		this.job_group = job_group;
		return this;
	}

	public String getJob_class_name() {
		return job_class_name;
	}

	public JobAndTrigger setJob_class_name(String job_class_name) {
		this.job_class_name = job_class_name;
		return this;
	}

	public String getTrigger_name() {
		return trigger_name;
	}

	public JobAndTrigger setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
		return this;
	}

	public String getTrigger_group() {
		return trigger_group;
	}

	public JobAndTrigger setTrigger_group(String trigger_group) {
		this.trigger_group = trigger_group;
		return this;
	}

	public BigInteger getRepeat_interval() {
		return repeat_interval;
	}

	public JobAndTrigger setRepeat_interval(BigInteger repeat_interval) {
		this.repeat_interval = repeat_interval;
		return this;
	}

	public BigInteger getTimes_triggered() {
		return times_triggered;
	}

	public JobAndTrigger setTimes_triggered(BigInteger times_triggered) {
		this.times_triggered = times_triggered;
		return this;
	}

	public String getCron_expression() {
		return cron_expression;
	}

	public JobAndTrigger setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
		return this;
	}

	public String getTime_zone_id() {
		return time_zone_id;
	}

	public JobAndTrigger setTime_zone_id(String time_zone_id) {
		this.time_zone_id = time_zone_id;
		return this;
	}

	public String getTrigger_type() {
		return trigger_type;
	}

	public JobAndTrigger setTrigger_type(String trigger_type) {
		this.trigger_type = trigger_type;
		return this;
	}

	public String getTrigger_state() {
		return trigger_state;
	}

	public JobAndTrigger setTrigger_state(String trigger_state) {
		this.trigger_state = trigger_state;
		return this;
	}
}
