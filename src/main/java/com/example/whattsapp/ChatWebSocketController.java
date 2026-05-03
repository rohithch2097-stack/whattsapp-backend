package com.example.whattsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.whattsapp.Message;
import com.example.whattsapp.MessageService;

@Controller
public class ChatWebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message message) {
        // Save message to DB
        Message saved = messageService.saveMessage(message);
        // Broadcast to all subscribers of the conversation
        messagingTemplate.convertAndSend("/topic/conversation/" + saved.getConversationId(), saved);
    }
}
