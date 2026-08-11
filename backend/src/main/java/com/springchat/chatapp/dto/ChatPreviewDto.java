package com.springchat.chatapp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatPreviewDto {
    Long chatId;
    Long otherUserId;
    String otherUsername;
    LocalDateTime lastUpdatedAt;
    
}
