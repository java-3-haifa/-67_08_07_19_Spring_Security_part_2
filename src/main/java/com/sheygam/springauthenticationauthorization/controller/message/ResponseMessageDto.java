package com.sheygam.springauthenticationauthorization.controller.message;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ResponseMessageDto {
    public String id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime dateTime;
    public String message;
}
