package com.dumbo.server.config;

import com.dumbo.server.mqtt.ReceiveMessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.Random;

/**
 * --Mqtt配置类
 *
 * Created by Dumbo on 2018/8/26
 **/

@Configuration
public class MqttConfig {
    @Value("${mqtt.broker.serverUri}")
    private String serviceUri ;

    @Value("${mqtt.broker.username:}")
    private String username ;

    @Value("${mqtt.broker.password:}")
    private String password ;

    @Value("${mqtt.inboundclientId}")
    private String clientId ;

    @Value("${mqtt.qos}")
    private int qos = 1;

    @Value("${mqtt.completionTimeout}")
    private int completionTimeout ;

    private static String ONLINE = "online";

    private Random rand = new Random();

    /**
     * 创建mqtt的连接工厂，连接apollo代理服务器
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs(serviceUri);
        factory.setUserName(username);
        factory.setPassword(password);
        //factory.setKeepAliveInterval(20);
        factory.setCleanSession(false);
        return factory;
    }

    /**
     * 生产者（发布消息）
     * */
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel") // 绑定生产者
    public MqttPahoMessageHandler mqttOutbound(MqttPahoClientFactory mqttClientFactory) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                clientId + String.format("%05d", rand.nextInt(10000)), mqttClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(1);
        messageHandler.setDefaultRetained(false);
        messageHandler.setAsyncEvents(false);
        return messageHandler;
    }

    /**
     * 消费者（订阅消息）
     * */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel") // 绑定消费者
    public MessageHandler handler() {
        return new ReceiveMessageHandler();
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter;
        adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId + String.format("%05d", rand.nextInt(10000)), mqttClientFactory(), "/data");
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

}
