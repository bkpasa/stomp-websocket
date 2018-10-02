package com.bkpasa.stomp.websocket.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.bkpasa.stomp.websocket.framework.WebSocketConfigurer;
import com.bkpasa.stomp.websocket.rest.controller.WebSocketController;


@Configuration
@EnableWebSocketMessageBroker
public class BeanConfiguration {
    @Bean
    WebSocketMessageBrokerConfigurer webSockerConfigurer() {
        return new WebSocketConfigurer();
    }

    @Bean
    WebSocketStompClient stompClient() {
        WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(simpleWebSocketClient));
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient;
    }

    @Bean
    WebSocketController webSocketController() {
        return new WebSocketController();
    }
}
