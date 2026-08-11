package com.springchat.chatapp.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatUserPrincipal{
    private Long id;

    private String name;
    
    private String email;

    private String username;

}