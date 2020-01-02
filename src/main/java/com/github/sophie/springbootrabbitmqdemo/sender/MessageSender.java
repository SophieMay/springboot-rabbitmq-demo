package com.github.sophie.springbootrabbitmqdemo.sender;

import com.github.sophie.springbootrabbitmqdemo.constant.MqConstant;
import com.github.sophie.springbootrabbitmqdemo.reciver.UserDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * send message
 *
 * @author sophie
 * @date 2019-08-19 18:28
 */
@Component
@RestController
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void send() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("sophie");
        userDTO.setAge(18);
        for (int i = 0; i < 1000; i++) {
            System.out.println("发送第" + i + "条消息成功");
            rabbitTemplate.convertAndSend(MqConstant.QUEUE_EXCHANGE_NAME, MqConstant.QUEUE_ROUTING_KEY,
                    userDTO);
        }
    }


}
