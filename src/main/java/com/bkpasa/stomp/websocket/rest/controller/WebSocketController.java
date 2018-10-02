package com.bkpasa.stomp.websocket.rest.controller;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.bkpasa.stomp.websocket.domain.model.ASimpleMessage;
import com.bkpasa.stomp.websocket.domain.model.Greeting;
import com.bkpasa.stomp.websocket.framework.GreetingStompSessionHandler;

@RestController
@RequestMapping(value = "/rest")
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    WebSocketStompClient stompClient;

    @Value("ws://localhost:${server.port}/simple-websocket")
    private String url;

    private final AtomicBoolean isSubscribed = new AtomicBoolean(false);

    @RequestMapping(value = "/sendUsingStompClient", produces = {
            MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
    public String sendUsingStompClient() throws InterruptedException, ExecutionException {
        StompSessionHandler sessionHandler = new GreetingStompSessionHandler();
        StompSession session = stompClient.connect(url, sessionHandler).get();

        if (isSubscribed.compareAndSet(false, true)) {// subscribe to it only once
            session.subscribe("/topic/greetings", new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return Greeting.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    System.out.println(payload.toString());
                }

            });
        }
        session.send("/app/hello", new ASimpleMessage("Mark"));
        return "sendUsingStompClient successful";
    }

    @RequestMapping(value = "/sendUsingMessageTemplate", produces = {
            MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
    public String sendUsingMessageTemplate() {
        messagingTemplate.convertAndSend("/topic/greetings", new Greeting("new greeting"));
        return "sendUsingMessageTemplate successful";
    }
}
