package com.lars.www.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql
 * @since 2019-07-31
 */
@TableName("lease_region_info")
public class LeaseRegionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 区域名称
	 */
	@TableField(value="region_name")
	private String regionName;

	/**
	 * 区域国家
	 */
	@TableField(value="region_country")
	private String regionCountry;

	/**
	 * 计费方案id
	 */
	@TableField(value="case_id")
	private Integer caseId;

	/**
	 * 状态 0禁用 1启用
	 */
	private Integer status;

	/**
	 * 区域gps
	 */
	@TableField(value="region_gps")
	private String regionGps;

	/**
	 * 中心点经度
	 */
	@TableField(value="center_lon")
	private String centerLon;

	/**
	 * 中心点纬度
	 */
	@TableField(value="center_lat")
	private String centerLat;

	/**
	 * 图形类型
	 */
	@TableField(value="graph_type")
	private String graphType;

	/**
	 * 时区
	 */
	@TableField(value="time_zone")
	private String timeZone;

	/**
	 * 客服电话
	 */
	@TableField(value="service_phone")
	private String servicePhone;

	/**
	 * 直营商/代理商id
	 */
	@TableField(value="operator_id")
	private Integer operatorId;

	/**
	 * 创建时间
	 */
	@TableField(value="gmt_create")
	private Date gmtCreate;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public LeaseRegionInfo setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getRegionName() {
		return regionName;
	}

	public LeaseRegionInfo setRegionName(String regionName) {
		this.regionName = regionName;
		return this;
	}

	public String getRegionCountry() {
		return regionCountry;
	}

	public LeaseRegionInfo setRegionCountry(String regionCountry) {
		this.regionCountry = regionCountry;
		return this;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public LeaseRegionInfo setCaseId(Integer caseId) {
		this.caseId = caseId;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public LeaseRegionInfo setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getRegionGps() {
		return regionGps;
	}

	public LeaseRegionInfo setRegionGps(String regionGps) {
		this.regionGps = regionGps;
		return this;
	}

	public String getCenterLon() {
		return centerLon;
	}

	public LeaseRegionInfo setCenterLon(String centerLon) {
		this.centerLon = centerLon;
		return this;
	}

	public String getCenterLat() {
		return centerLat;
	}

	public LeaseRegionInfo setCenterLat(String centerLat) {
		this.centerLat = centerLat;
		return this;
	}

	public String getGraphType() {
		return graphType;
	}

	public LeaseRegionInfo setGraphType(String graphType) {
		this.graphType = graphType;
		return this;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public LeaseRegionInfo setTimeZone(String timeZone) {
		this.timeZone = timeZone;
		return this;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public LeaseRegionInfo setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
		return this;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public LeaseRegionInfo setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
		return this;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public LeaseRegionInfo setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}
}
