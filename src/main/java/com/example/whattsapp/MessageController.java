package com.example.whattsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/conversation/{conversationId}")
    public List<Message> getMessagesByConversation(@PathVariable Long conversationId) {
        return messageService.getMessagesByConversationId(conversationId);
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public Message createMessage(@RequestBody MessageWithReceiver messageWithReceiver) {
        Message message = messageWithReceiver.getMessage();
        Long receiverId = messageWithReceiver.getReceiverId();
        return messageService.saveMessageWithAutoConversation(message, receiverId);
    }

    // DTO for message + receiverId
    public static class MessageWithReceiver {
        private Message message;
        private Long receiverId;
        public Message getMessage() { return message; }
        public void setMessage(Message message) { this.message = message; }
        public Long getReceiverId() { return receiverId; }
        public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
    }
}
