package com.mqttlog.www.mqtt.inbound;

import com.alibaba.fastjson.JSONObject;
import com.mqttlog.www.mqtt.entity.MqttDownLinkBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * MQTT入站消息处理器 => 处理设备平台推送上来的设备消息
 * @Author LuDash
 * @Date 2017-10-18 20:17
 */
@Component
public class MqttInboundHandler {

    private Logger logger = LoggerFactory.getLogger(MqttInboundHandler.class);

    @ServiceActivator(inputChannel="mqttInboundChannel")
    public void handleMessage(Message<?> message) {
        try {
            MqttDownLinkBody body = JSONObject.parseObject(message.getPayload().toString(), MqttDownLinkBody.class);
//            logger.info("MQTT Inbound : " + body.toString());

            //TODO 具体业务
            logger.info("MQTT Inbound : " + message.getPayload().toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
