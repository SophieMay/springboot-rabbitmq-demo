package com.github.sophie.springbootrabbitmqdemo.sender;

import com.alibaba.fastjson.JSONObject;
import com.github.sophie.springbootrabbitmqdemo.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * send message
 *
 * @author sophie
 * @date 2019-08-19 18:28
 */
@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("name", "Amy");
        jsonObject.fluentPut("age", 18);
        for (int i = 0; i < 10000; i++) {
            System.out.println("发送第" + i + "条消息成功");
            rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_EXCHANGE_NAME, RabbitMqConfig.QUEUE_ROUTING_KEY,
                    jsonObject);
        }
    }


}
