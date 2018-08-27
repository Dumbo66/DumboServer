package com.dumbo.server.controller;

import com.dumbo.server.mqtt.MqttMessageGateway;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dumbo on 2018/8/26
 **/

@RestController
@Api(tags = "Mqtt交互接口")
@RequestMapping("/api/v1")
public class MqttController {
    @Autowired
    private MqttMessageGateway mqttMessageGateway;

    @RequestMapping("/mqtt/send")
    public String sendMqttMessage(String message){
        mqttMessageGateway.sendMqtt(message,"mqtt-topic-demo");
        return "OK!";
    }
}
