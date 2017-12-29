package com.wuspace.controller.message;

import com.wuspace.domain.User;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class WebNotificationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greet")
    public MessageDto hello(MessageDto message) {
        System.out.println("message: " + message.getContent());
        message.setContent("greet come from server.");
        message.setUser(new User().id(Long.valueOf(1)).username("chao"));
        return message;
    }

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public void notifications() {
        String payload = "messages " + RandomUtils.nextLong();
        System.out.println(payload);
        // websocket
        simpMessagingTemplate.convertAndSend("/notifications", new MessageDto().content(payload));
        // rabbitmq
//        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, "Hello from RabbitMQ!");
//        try {
//            new Receiver().getLatch().await(10000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

}
