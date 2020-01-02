package com.github.sophie.springbootrabbitmqdemo.config;

import com.github.sophie.springbootrabbitmqdemo.constant.MqConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * config sender
 *
 * @author sophie
 * @date 2019-12-27 09:53
 */
@Component
public class RabbitSenderConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(MqConstant.QUEUE_EXCHANGE_NAME);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
