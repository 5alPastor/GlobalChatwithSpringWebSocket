package com.botman.websocketsglobalchat.config;

import com.botman.websocketsglobalchat.security.JwtHandshakeInterceptor;
import org.apache.logging.log4j.message.Message;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        //it enables memory-based broker for /topic (broadcast) and /queue (which is private)
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app"); // client ->server
        config.setUserDestinationPrefix("/user"); //server specific user
    }
    @Override
    public  void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws-chat")
                .addInterceptors(new JwtHandshakeInterceptor()) // JWT support
                .setAllowedOriginPatterns("*")
                .withSockJS();//fallback for older browsers
    }
}
