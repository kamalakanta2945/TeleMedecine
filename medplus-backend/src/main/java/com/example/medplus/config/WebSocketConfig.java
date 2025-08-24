package com.example.medplus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        // /topic for broadcast, /queue for private messages
//        config.enableSimpleBroker("/topic", "/queue"); 
//        config.setApplicationDestinationPrefixes("/app");  
//        config.setUserDestinationPrefix("/user");  // required for /user/<id>/queue/messages
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/chat")                       // WebSocket endpoint
//                .setAllowedOriginPatterns("http://localhost:3000") // React frontend
//                .withSockJS();                               // SockJS fallback
//    }
	
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); // broadcast & private
        config.setApplicationDestinationPrefixes("/app");  
        config.setUserDestinationPrefix("/user");  // for private messages
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOriginPatterns("http://localhost:3000") // React
                .withSockJS(); // fallback
    }
}
