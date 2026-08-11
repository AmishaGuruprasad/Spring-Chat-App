package com.springchat.chatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.springchat.chatapp.service.OAuthSuccessHandler;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthSuccessHandler oAuthSuccessHandler;


    @Bean
    SecurityFilterChain securityFilterChain(
        HttpSecurity http
    ) throws Exception {
    
        return http
            .authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
            )
            .oauth2Login(oauth -> oauth.
                successHandler(oAuthSuccessHandler)
            )
            .build();
    }
    /*
    Configures the security filter chain and returns it 
    if we dont configure one, spring uses its default login form
    HttpSecurity is like an empty object to which we are adding our own rules.
    first rule we added is authorizehttpp requests, here we are authorizing any request that is authenticated. we can have additional rules like if endpoint is /teacher the auth object should have role teacher etc
    .requestMatcher(/teacher/**).hasRole("teacher")

    then for oauth we are configuring our own success handler

    .build creates the filters, arranges them in order and returns the filter chain
     

    */
}

