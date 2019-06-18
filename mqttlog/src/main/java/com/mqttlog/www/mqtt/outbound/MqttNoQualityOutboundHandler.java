package com.mqttlog.www.mqtt.outbound;

import com.alibaba.fastjson.JSONObject;
import com.mqttlog.www.mqtt.config.MQTTSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * MQTT出站消息转换器
 *
 * @author Leaves
 * @version 1.0.0
 * @date 2018/3/1
 */
@Component
public class MqttNoQualityOutboundHandler {

    private Logger logger = LoggerFactory.getLogger(MqttNoQualityOutboundHandler.class);


    @Autowired
    private MQTTSettings mqttSettings;

    @Transformer(inputChannel = "mqttNoQualityMessageTransformChannel", outputChannel = "mqttNoQualityOutboundChannel")
    public Message<?> transform(Message<?> message) {
        JSONObject data = JSONObject.parseObject(message.getPayload().toString());

        //发布消息完整主题PubTopic=basePubTopic+topic（topic由应用服务程序指定）
        String publishTopic = mqttSettings.getBasePubTopic() + data.get("topic").toString();

        //发送数据到broker的时候设置发布消息的主题的方法：使用MessageBuilder，添加mqtt_topic头部信息(header)
        Message<?> msgBuild = MessageBuilder
                .fromMessage(message)
                .setHeaderIfAbsent(data.get("headerName").toString(), publishTopic)
                .build();
        logger.info("MQTT Outbound : " + message.getPayload().toString());
        return msgBuild;
    }

}
