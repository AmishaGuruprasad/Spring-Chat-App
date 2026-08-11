package com.springchat.chatapp.service;

import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Service
public class AuthService{
    
    public Long getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ((ChatUserPrincipal) auth.getPrincipal()).getId();
    }
}