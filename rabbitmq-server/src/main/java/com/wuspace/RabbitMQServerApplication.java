package com.wuspace;

import com.wuspace.domain.Receiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by WUCHAO on 2017/3/19.
 */
@SpringBootApplication
public class RabbitMQServerApplication {

    final static String QUEUENAME = "spring-boot";

    /**
     * Spring AMQP requires that the Queue, the TopicExchange,
     * and the Binding be declared as top level Spring beans in order to be set up properly.
     */

    // 创建一个 AMQP 队列
    @Bean
    protected Queue queue() {
        return new Queue(QUEUENAME, false);
    }

    // 创建一个 topic 交换器
    @Bean
    protected Exchange exchange() {
        return new TopicExchange("spring-boot-topic-exchange");
    }

    // 绑定队列和交换器
    @Bean
    protected Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(QUEUENAME);
    }

    // 注册消息监听器
    @Bean
    protected MessageListenerAdapter listenerAdapter(Receiver receiver) {
        // 监听 Receiver 类中的 receiveMessage() 方法
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    protected SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory,
                                                               MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUENAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQServerApplication.class);
    }
}
