package com.example.whattsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private ConversationParticipantService participantService;

    public List<Message> getMessagesByConversationId(Long conversationId) {
        return messageRepository.findByConversationIdOrderByTimestampAsc(conversationId);
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message saveMessageWithAutoConversation(Message message, Long receiverId) {
        Long senderId = message.getSenderId();
        Long conversationId = message.getConversationId();
        // If conversationId is missing or invalid, find or create conversation
        if (conversationId == null || conversationId == 0L) {
            Optional<Conversation> existing = conversationService.findDirectConversationBetweenUsers(senderId, receiverId);
            Conversation conversation = existing.orElseGet(() -> {
                Conversation newConv = new Conversation();
                newConv.setGroup(false);
                newConv.setName("Direct Chat");
                Conversation saved = conversationService.saveConversation(newConv);
                // Add both users as participants
                ConversationParticipant p1 = new ConversationParticipant();
                p1.setConversation(saved);
                User u1 = new User();
                u1.setId(senderId);
                p1.setUser(u1);
                participantService.saveParticipant(p1);
                ConversationParticipant p2 = new ConversationParticipant();
                p2.setConversation(saved);
                User u2 = new User();
                u2.setId(receiverId);
                p2.setUser(u2);
                participantService.saveParticipant(p2);
                return saved;
            });
            message.setConversationId(conversation.getId());
        }
        return messageRepository.save(message);
    }
}
