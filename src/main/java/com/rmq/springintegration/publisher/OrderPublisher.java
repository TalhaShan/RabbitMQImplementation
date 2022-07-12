package com.rmq.springintegration.publisher;

import com.rmq.springintegration.config.MessageConfig;
import com.rmq.springintegration.dto.Order;
import com.rmq.springintegration.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostMapping("/{restaurantName}")
    private String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {

        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully in " + restaurantName);

        rabbitTemplate.convertAndSend(MessageConfig.WAVETECH_EXCHANGE, MessageConfig.WAVETECH_ROUTING_KEY, orderStatus);
        return "Success";
    }

}
