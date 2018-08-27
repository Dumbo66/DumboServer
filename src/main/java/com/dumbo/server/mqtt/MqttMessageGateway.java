package com.dumbo.server.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * --消息发送接口，不需要实现，spring会通过代理的方式实现
 *
 * Created by Dumbo on 2018/8/26
 **/

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttMessageGateway {
    void sendMqtt(String data,@Header(MqttHeaders.TOPIC) String topic);
}
