package com.springchat.chatapp.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springchat.chatapp.entity.Chat;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{

    @Query("""
        select c from Chat c
        where (c.user1.id = :userId or c.user2.id = :userId)
        and (:cursor is null or c.lastUpdated < :cursor or (c.lastUpdated = :cursor and c.id < :cursorId))
        order by c.lastUpdated desc, c.id desc  
    """)
    List<Chat> findChats( 
    @Param("userId") Long userId, 
    @Param("cursor") LocalDateTime cursor, 
    @Param("cursorId") Long cursorId,
    Pageable pageable);

    @Query("""
    select count(c)>0
    from Chat c
    where c.id = :chatId and
    (c.user1.id = :userId or c.user2.id = :userId)""")
    boolean isParticipant(@Param("chatId") Long chatId, @Param("userId") Long userId);

}