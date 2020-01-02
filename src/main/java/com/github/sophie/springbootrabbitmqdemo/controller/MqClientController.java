package com.github.sophie.springbootrabbitmqdemo.controller;


import com.github.sophie.springbootrabbitmqdemo.dto.QueueDTO;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * controller
 *
 * @author sophie
 * @date 2019-12-30 15:10
 */

@Component
@RestController
public class MqClientController {

    @Autowired
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public void stopAll() {
        this.rabbitListenerEndpointRegistry.stop();
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void start() {
        this.rabbitListenerEndpointRegistry.start();
    }

    @RequestMapping(value = "/stopq", method = RequestMethod.POST)
    public boolean stop(@RequestBody QueueDTO queueName) {
        Collection<MessageListenerContainer> listenerContainers = rabbitListenerEndpointRegistry.getListenerContainers();
        for (MessageListenerContainer listenerContainer : listenerContainers) {
            if (this.isQueueListener(queueName.getQueueName(), listenerContainer)) {
                listenerContainer.stop();
                return true;
            }
        }
        return false;
    }

    private boolean isQueueListener(String queueName, MessageListenerContainer listenerContainer) {
        if (listenerContainer instanceof AbstractMessageListenerContainer) {
            AbstractMessageListenerContainer abstractMessageListenerContainer = (AbstractMessageListenerContainer) listenerContainer;
            String[] queueNames = abstractMessageListenerContainer.getQueueNames();
            return ArrayUtils.contains(queueNames,queueName);
        }
        return false;
    }


}
