package com.github.sophie.springbootrabbitmqdemo.reciver;

import com.github.sophie.springbootrabbitmqdemo.constant.MqConstant;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * reciver
 *
 * @author sophie
 * @date 2019-08-19 18:23
 */
@Configuration
public class MessageReciver {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = MqConstant.QUEUE_EXCHANGE_NAME, type = "topic"),
            value = @Queue(value = MqConstant.QUEUE_NAME),
            key = MqConstant.QUEUE_ROUTING_KEY
    ))
    public void process(Map<String, Object> mes) {
        if (null == mes) {
            System.out.println("mes 为空");
        } else {
            System.out.println("mes = [" + mes + "]");
        }
    }

}
