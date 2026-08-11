package com.springchat.chatapp.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springchat.chatapp.entity.Message;

import java.util.List;

import org.springframework.data.domain.Pageable;


public interface MessageRepository extends JpaRepository<Message, Long>{

    @Query("""
    select m from Message m where m.chat.id = :chatId and
    (:cursor is null 
    or m.timestamp < :cursor
    or (m.timestamp = :cursor and m.id < :cursorId))
    order by m.timestamp desc, m.id desc""")
    List<Message> findMessages(
    @Param("chatId") Long chatId, 
    @Param("cursor") LocalDateTime cursor, 
    @Param("cursorId") Long cursorId, 
    Pageable pageable);
    
}
