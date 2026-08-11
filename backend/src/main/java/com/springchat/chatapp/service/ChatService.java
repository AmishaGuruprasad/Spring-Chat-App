package com.springchat.chatapp.service;

import java.time.LocalDateTime;

import com.springchat.chatapp.dto.ChatPreviewDto;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springchat.chatapp.dto.ChatsPageDto;
import com.springchat.chatapp.dto.CursorDto;
import com.springchat.chatapp.dto.MessageListDto;
import com.springchat.chatapp.dto.MessageDto;


import com.springchat.chatapp.repository.ChatRepository;
import com.springchat.chatapp.entity.Chat;
import com.springchat.chatapp.entity.User;
import com.springchat.chatapp.entity.Message;
import com.springchat.chatapp.repository.MessageRepository;

import com.springchat.chatapp.exceptions.chatExceptions.AccessDeniedException;
import com.springchat.chatapp.exceptions.chatExceptions.ChatNotFoundException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ChatService {

    private final AuthService authService;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public ChatsPageDto getChats(LocalDateTime cursor, Long cursorId){
        Long userId = authService.getCurrentUserId();


        List<Chat> chats = chatRepository.findChats(userId, cursor, cursorId, PageRequest.of(0, 30));

        List<ChatPreviewDto> chatPreviews = 
            chats.stream()
            .map(chat->{
                User otherUser = chat.getUser1().getId().equals(userId)? chat.getUser2() : chat.getUser1();

                return new ChatPreviewDto( chat.getId(), otherUser.getId(), otherUser.getUsername(), chat.getLastUpdated());
            }).toList();
        
        LocalDateTime nextCursor = chatPreviews.isEmpty()? null : chatPreviews.get(chatPreviews.size()-1).getLastUpdatedAt();
        Long nextCursorId = chatPreviews.isEmpty()? null : chatPreviews.get(chatPreviews.size()-1).getChatId();

        return new ChatsPageDto(chatPreviews, new CursorDto(nextCursor, nextCursorId));  

    }

    public MessageListDto getMessages(Long chatId, LocalDateTime cursor, Long cursorId){

        if (!chatRepository.existsById(chatId))
            throw new ChatNotFoundException();
        
        Long userId = authService.getCurrentUserId();

        boolean isParticipant = chatRepository.isParticipant(chatId, userId);

        if (!isParticipant){
            throw new AccessDeniedException();
        }

        List<Message> messages = messageRepository.findMessages(chatId, cursor, cursorId, PageRequest.of(0, 50));

        List<MessageDto> messageDtoList = 
        messages.stream()
        .map(message->{
            return new MessageDto(message.getId(), message.getSender().getId(), message.getContent(), message.getTimestamp(), message.getReadAt());
        }).toList();

        LocalDateTime nextCursor = messageDtoList.isEmpty()? null : messageDtoList.get(messageDtoList.size()-1).getTimestamp();
        Long nextCursorId = messageDtoList.isEmpty()? null : messageDtoList.get(messageDtoList.size()-1).getId();

        return new MessageListDto(messageDtoList, new CursorDto(nextCursor, nextCursorId));

        }

        public void markAsRead(Long chatId, LocalDateTime lastReadTime, Long LastReadMessageId
    
}
