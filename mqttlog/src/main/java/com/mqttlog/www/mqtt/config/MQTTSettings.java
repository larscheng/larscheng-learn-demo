package com.mqttlog.www.mqtt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MQTT的外部文件（application.yml）配置读取类
 * @Author LuDash
 * @Date 2017-10-18 20:09
 */
@ConfigurationProperties(prefix = "mqtt")
public class MQTTSettings {

    private String broker;
    private String userName;
    private String password;
    private String subTopic;
    private String basePubTopic;
    private String caCrtFile;
    private String clientCrtFile;
    private String clientKeyFile;
    private String sslPassword;

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public String getBasePubTopic() {
        return basePubTopic;
    }

    public void setBasePubTopic(String basePubTopic) {
        this.basePubTopic = basePubTopic;
    }

    public String getCaCrtFile() {
        return caCrtFile;
    }

    public void setCaCrtFile(String caCrtFile) {
        this.caCrtFile = caCrtFile;
    }

    public String getClientCrtFile() {
        return clientCrtFile;
    }

    public void setClientCrtFile(String clientCrtFile) {
        this.clientCrtFile = clientCrtFile;
    }

    public String getClientKeyFile() {
        return clientKeyFile;
    }

    public void setClientKeyFile(String clientKeyFile) {
        this.clientKeyFile = clientKeyFile;
    }

    public String getSslPassword() {
        return sslPassword;
    }

    public void setSslPassword(String sslPassword) {
        this.sslPassword = sslPassword;
    }
}
