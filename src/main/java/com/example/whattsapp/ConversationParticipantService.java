package com.example.whattsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationParticipantService {
    @Autowired
    private ConversationParticipantRepository conversationParticipantRepository;

    public List<ConversationParticipant> getParticipantsByUserId(Long userId) {
        return conversationParticipantRepository.findByUserId(userId);
    }

    public List<ConversationParticipant> getParticipantsByConversationId(Long conversationId) {
        return conversationParticipantRepository.findByConversationId(conversationId);
    }

    public Optional<ConversationParticipant> getById(Long id) {
        return conversationParticipantRepository.findById(id);
    }

    public ConversationParticipant saveParticipant(ConversationParticipant participant) {
        return conversationParticipantRepository.save(participant);
    }
}

