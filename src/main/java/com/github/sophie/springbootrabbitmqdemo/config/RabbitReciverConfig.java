package com.github.sophie.springbootrabbitmqdemo.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * config rabbitMQ
 *
 * @author sophie
 * @date 2019-08-19 18:17
 */
@Configuration
public class RabbitReciverConfig {

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
