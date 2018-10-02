package com.bkpasa.stomp.websocket.message.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.bkpasa.stomp.websocket.domain.model.ASimpleMessage;
import com.bkpasa.stomp.websocket.domain.model.Greeting;

@Controller
public class GreetingController {

    @MessageMapping("/hello") // this the endpoint where the websocket sends message, the effective endpoint is
                              // caluclated by prepending ApplicationDestinationPrefixes in WebSocketConfigurer
    @SendTo("/topic/greetings") // the endpoint to subscribe to
    public Greeting hello(ASimpleMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()));
    }
}
