package com.bkpasa.stomp.websocket.framework;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import com.bkpasa.stomp.websocket.domain.model.Greeting;

public class GreetingStompSessionHandler implements StompSessionHandler {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {

    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
            Throwable exception) {

    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {

    }

}
