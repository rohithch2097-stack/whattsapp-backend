package com.example.whattsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    public Optional<Conversation> getConversationById(Long id) {
        return conversationRepository.findById(id);
    }

    public Optional<Conversation> findDirectConversationBetweenUsers(Long userId1, Long userId2) {
        return conversationRepository.findDirectConversationBetweenUsers(userId1, userId2);
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }
}
