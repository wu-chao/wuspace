package com.github.wuchao.webproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 参考：spring4 使用websocket（https://www.cnblogs.com/nevermorewang/p/7274217.html）
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 创建消息代理,相当于mq服务器,它位于内存中,页面订阅后可以从这里获取消息
        registry.enableSimpleBroker("/topic", "/notifications");
        // 页面请求映射到ws处理器的映射前缀,即将请求映射到 @MessageMapping
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册 websocket 的端点, 客户端需要注册这个端点进行链接
        registry.addEndpoint("/ws-server").setAllowedOrigins("*").withSockJS();
    }
}
