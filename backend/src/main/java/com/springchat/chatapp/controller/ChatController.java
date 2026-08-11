package com.springchat.chatapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springchat.chatapp.dto.ChatsPageDto;

import lombok.RequiredArgsConstructor;

import com.springchat.chatapp.service.ChatService;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springchat.chatapp.dto.MessageListDto;



@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {
    
    private final ChatService chatService;

    @GetMapping("/")
    public ChatsPageDto getChats(@RequestParam(required = false) LocalDateTime cursor, 
    @RequestParam(required=false) Long cursorId){
        return chatService.getChats(cursor, cursorId);
    }

    @GetMapping("/{chatId}/messages")
    public MessageListDto getChat(@PathVariable Long chatId, 
    @RequestParam(required=false) LocalDateTime cursor, 
    @RequestParam(required=false) Long cursorId){
        return chatService.getMessages(chatId, cursor, cursorId);
    }

    @PostMapping("/{chatId}/read-reciept")
    public void markAsRead(@PathVariable Long chatId,
    @RequestParam LocalDateTime lastReadTime, 
    @RequestParam Long lastReadMessageId){
        chatService.readMessages(chatId, lastReadTime, lastReadMessageId);

    }

    
}
