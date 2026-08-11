package com.springchat.chatapp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDto {
    Long id;
    Long senderId;
    String content;
    LocalDateTime timestamp;
    LocalDateTime readAt;
}
