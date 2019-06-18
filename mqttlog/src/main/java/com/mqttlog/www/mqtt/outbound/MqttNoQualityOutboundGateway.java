package com.mqttlog.www.mqtt.outbound;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * MQTT消息出站网关
 *
 * @author Leaves
 * @version 1.0.0
 * @date 2018/3/1
 */
@MessagingGateway(defaultRequestChannel = "mqttNoQualityMessageTransformChannel")
public interface MqttNoQualityOutboundGateway {

    /**
     * 发布消息到MQTT服务器
     *
     * @param message
     */
    void publish(String message);

}
