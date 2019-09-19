/**
 * 
 */
package com.lars.www.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumJobClassType
 * @Description         :
 * @Author              : xujialin
 * @CreationDate        : 2018年1月17日下午2:20:44
 */
public enum EnumJobClassType {
	/**
	 * 
	 */
	SendMsgJob(1, "com.lars.www.job.SendMsgJob"),
	RegionCheckJob(2, "com.lars.www.job.RegionCheckJob"),
	RegionAddJob(3, "com.lars.www.job.RegionAddJob");

	private Integer value;

	private String name;

	/**
	 * 全局索引池
	 */
	private static Map<Integer, EnumJobClassType> pool = new HashMap<Integer, EnumJobClassType>();
	static {
		for (EnumJobClassType et : EnumJobClassType.values()) {
			pool.put(et.value, et);
		}
	}

	public static Map<Integer, String> toMap() {
		Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
		for (EnumJobClassType type : EnumJobClassType.values()) {
			enumDataMap.put(type.getValue(), type.getName());
		}
		return enumDataMap;
	}

	public static String getValueByIndex(Integer index) {
		Map map = toMap();
		return map.get(index).toString();
	}

	/**
	 * 根据内容索引
	 *
	 * @param value
	 * @return
	 */
	public static EnumJobClassType indexByValue(Integer value) {
		return pool.get(value);
	}

	private EnumJobClassType(Integer value, String name) {
	        this.value = value;
	        this.name = name;
	    }

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
