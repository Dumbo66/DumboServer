package com.dumbo.server.mqtt;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * Created by Dumbo on 2018/8/26
 **/

public class ReceiveMessageHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String str = (String) message.getPayload();
        System.out.println("接收到消息：" + str);
    }
}
