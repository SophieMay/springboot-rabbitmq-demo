package com.github.sophie.springbootrabbitmqdemo;

import com.github.sophie.springbootrabbitmqdemo.sender.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test mq
 *
 * @author sophie
 * @date 2019-08-19 18:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {

    @Autowired
    private MessageSender messageSender;

    @Test
    public void testProducer() {
        messageSender.send();
    }
}
