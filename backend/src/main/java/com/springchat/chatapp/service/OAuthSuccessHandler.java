package com.springchat.chatapp.service;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.springchat.chatapp.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.springchat.chatapp.entity.User;

import jakarta.servlet.http.HttpServletRequest;



@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler{

    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth){
        OAuth2User oauth2user = (OAuth2User)auth.getPrincipal();

        String email = oauth2user.getAttribute("email");

        String name = oauth2user.getAttribute("name");

        Optional<User> user = userRepository.findByEmail(email);
        
        User dbUser;
        if (user.isPresent()){
            dbUser = user.get();
        }
        else{
            dbUser = User.builder().email(email).username(email.split("@")[0]).name(name).build();
        }

        userRepository.save(dbUser);

        ChatUserPrincipal principal = new ChatUserPrincipal(
            dbUser.getId(),
            dbUser.getName(),
            dbUser.getEmail(),
            dbUser.getUsername()
        );

        Authentication newAuth = new UsernamePasswordAuthenticationToken(
            principal,
            null,
            List.of()
        );

        SecurityContextHolder.getContext().setAuthentication(newAuth);

        try{
            res.sendRedirect("/me"); 
        }
        catch(Exception e){
            System.out.println(e);
        }       
        
/*
principal stores the cur user
we get the oatuh user from the principal,
find or create our application user
set the new auth object as our app user
*/

    }






    
}
