package com.springchat.chatapp.dto;

import lombok.AllArgsConstructor;
import java.util.*;


@AllArgsConstructor
public class MessageListDto{
    List<MessageDto> messages;
    CursorDto nextCursor;

}