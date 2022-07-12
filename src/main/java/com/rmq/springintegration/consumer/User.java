package com.rmq.springintegration.consumer;

import com.rmq.springintegration.config.MessageConfig;
import com.rmq.springintegration.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessageConfig.WAVETECH_QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {

        System.out.println("Message received form queue " + orderStatus);

    }
}
