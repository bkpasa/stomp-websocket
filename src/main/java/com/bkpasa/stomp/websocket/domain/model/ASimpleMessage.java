package com.bkpasa.stomp.websocket.domain.model;

public class ASimpleMessage {
    private String name;

    public ASimpleMessage() {
        super();
    }

    public ASimpleMessage(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
