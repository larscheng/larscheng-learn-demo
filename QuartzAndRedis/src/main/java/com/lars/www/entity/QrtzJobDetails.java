package com.lars.www.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql
 * @since 2019-07-30
 */
@TableName("qrtz_job_details")
public class QrtzJobDetails implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableField(value="SCHED_NAME")
	private String schedName;

	/**
	 * 
	 */
	@TableField(value="JOB_NAME")
	private String jobName;

	/**
	 * 
	 */
	@TableField(value="JOB_GROUP")
	private String jobGroup;

	/**
	 * 
	 */
	private String description;

	/**
	 * 
	 */
	@TableField(value="JOB_CLASS_NAME")
	private String jobClassName;

	/**
	 * 
	 */
	@TableField(value="IS_DURABLE")
	private String isDurable;

	/**
	 * 
	 */
	@TableField(value="IS_NONCONCURRENT")
	private String isNonconcurrent;

	/**
	 * 
	 */
	@TableField(value="IS_UPDATE_DATA")
	private String isUpdateData;

	/**
	 * 
	 */
	@TableField(value="REQUESTS_RECOVERY")
	private String requestsRecovery;

	/**
	 * 
	 */
	@TableField(value="JOB_DATA")
	private byte[] jobData;



	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getIsDurable() {
		return isDurable;
	}

	public void setIsDurable(String isDurable) {
		this.isDurable = isDurable;
	}

	public String getIsNonconcurrent() {
		return isNonconcurrent;
	}

	public void setIsNonconcurrent(String isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}

	public String getIsUpdateData() {
		return isUpdateData;
	}

	public void setIsUpdateData(String isUpdateData) {
		this.isUpdateData = isUpdateData;
	}

	public String getRequestsRecovery() {
		return requestsRecovery;
	}

	public void setRequestsRecovery(String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}

	public byte[] getJobData() {
		return jobData;
	}

	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}

}
