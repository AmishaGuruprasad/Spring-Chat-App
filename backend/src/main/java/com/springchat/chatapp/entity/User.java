package com.springchat.chatapp.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@Entity
@Getter //generally using direct setters is a problem because then it igives it freedom to set types that are not meant to be uset like createdat, same problem with using builder directly
@NoArgsConstructor //hibernate may require it internally 
@Builder
@AllArgsConstructor
@Table(name = "users") 
public class User{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true, nullable = false, length = 255)
    @NotBlank
    @Size(max = 255)
    private String email;

    @Column(unique = true, nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String username;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String name;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
}
