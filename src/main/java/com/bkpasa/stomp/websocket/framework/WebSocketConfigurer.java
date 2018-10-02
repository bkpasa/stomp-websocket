package com.bkpasa.stomp.websocket.framework;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class WebSocketConfigurer implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");// to enable a simple memory-based message broker to carry the
                                            // messages back to the client on destinations prefixed with "/topic"
        config.setApplicationDestinationPrefixes("/app");// prefix for messages that are bound for @MessageMapping
                                                         // annotated methods
    }

    // this is handshaking connection endpoint, used by sockJs connect
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/simple-websocket").withSockJS();
    }
}
