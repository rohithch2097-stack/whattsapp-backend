package com.example.whattsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participants")
public class ConversationParticipantController {
    @Autowired
    private ConversationParticipantService participantService;

    @GetMapping("/user/{userId}")
    public List<ConversationParticipant> getParticipantsByUser(@PathVariable Long userId) {
        return participantService.getParticipantsByUserId(userId);
    }

    @GetMapping("/conversation/{conversationId}")
    public List<ConversationParticipant> getParticipantsByConversation(@PathVariable Long conversationId) {
        return participantService.getParticipantsByConversationId(conversationId);
    }

    @GetMapping("/{id}")
    public Optional<ConversationParticipant> getById(@PathVariable Long id) {
        return participantService.getById(id);
    }

    @PostMapping
    public ConversationParticipant addParticipant(@RequestBody ConversationParticipant participant) {
        return participantService.saveParticipant(participant);
    }
}

