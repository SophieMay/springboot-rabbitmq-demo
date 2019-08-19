package com.github.sophie.springbootrabbitmqdemo.config;

import com.github.sophie.springbootrabbitmqdemo.reciver.MessageReciver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * config rabbitMQ
 *
 * @author sophie
 * @date 2019-08-19 18:17
 */
@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_ROUTING_KEY = "routing-key";
    public static final String QUEUE_NAME = "test-hello-demo";
    public static final String QUEUE_EXCHANGE_NAME = "sophie";

    @Bean
    public Queue queue() {
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(QUEUE_NAME, durable, exclusive, autoDelete);
    }

    @Bean
    public TopicExchange exchange() {
        // 是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new TopicExchange(QUEUE_EXCHANGE_NAME, durable, autoDelete);
    }

    /**
     * 绑定exchange和queue
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_ROUTING_KEY);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("*");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("*");
        connectionFactory.setPassword("*");
        connectionFactory.setVirtualHost("/");
        //必须要设置,消息的回调
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 第二个参数对应消息接收者的消息接受方法
     *
     * @param messageReciver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(MessageReciver messageReciver) {
        return new MessageListenerAdapter(messageReciver, "processString");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
