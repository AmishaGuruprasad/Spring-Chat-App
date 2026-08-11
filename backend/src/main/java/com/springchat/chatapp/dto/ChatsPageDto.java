package com.springchat.chatapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChatsPageDto {

    List<ChatPreviewDto> chatsPreviews;
    CursorDto nextCursor;
    
}
