package com.mqttlog.www.mqtt.outbound;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * MQTT消息出站网关
 *
 * @Author LuDash
 * @Date 2017-10-18 20:19
 */
@MessagingGateway(defaultRequestChannel = "mqttMessageTransformChannel")
public interface MqttOutboundGateway {

    /**
     * 发布消息到MQTT服务器
     *
     * @param message
     */
    void publish(String message);

}
