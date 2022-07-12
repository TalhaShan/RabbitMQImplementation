package com.rmq.springintegration.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    public static final String WAVETECH_QUEUE = "wavetech_queue";
    public static final String WAVETECH_EXCHANGE = "wavetech_exchange";
    public static final String WAVETECH_ROUTING_KEY = "wavetech_routing_key";

    @Bean
    public Queue queue() {
        return new Queue(WAVETECH_QUEUE);

    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(WAVETECH_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange)
                .with(WAVETECH_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
