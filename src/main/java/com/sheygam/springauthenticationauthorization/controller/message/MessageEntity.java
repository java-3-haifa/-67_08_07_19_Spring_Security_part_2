package com.sheygam.springauthenticationauthorization.controller.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageEntity {
    public UUID id;
    public LocalDateTime dateTime;
    public String message;


    public MessageEntity() {
    }

    public MessageEntity(UUID id, LocalDateTime dateTime, String message) {
        this.id = id;
        this.dateTime = dateTime;
        this.message = message;
    }
}
