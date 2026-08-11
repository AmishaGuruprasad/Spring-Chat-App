package com.springchat.chatapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springchat.chatapp.service.ChatUserPrincipal;



@RestController
public class TestController {

    @GetMapping("/me")
    public Object me(
            Authentication auth
    ) {
        return ((ChatUserPrincipal)(auth.getPrincipal())).getUsername();
    }
}