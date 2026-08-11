package com.springchat.chatapp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CursorDto {

    LocalDateTime cursor;
    Long cursorId;
    
}
