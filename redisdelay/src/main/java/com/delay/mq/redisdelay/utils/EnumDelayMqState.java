package com.delay.mq.redisdelay.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zhengql
 * @date
 */

public enum EnumDelayMqState {

    WAITING(0, "延时中"),

    CHARGING(1, "等待执行"),


    NOREND(2, "正在执行"),

 
    LEAEND(3, "执行失败"),

    ;

    private Integer value;

    private String name;

    EnumDelayMqState(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumDelayMqState> pool = new HashMap<>();

    static {
        for (EnumDelayMqState et : EnumDelayMqState.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<>();
        for (EnumDelayMqState type : EnumDelayMqState.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     *
     * @param value
     * @return
     */
    public static EnumDelayMqState indexByValue(Integer value) {
        return pool.get(value);
    }

}
