package com.example.whattsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ConversationParticipantService participantService;

    @GetMapping
    public List<Conversation> getAllConversations() {
        return conversationService.getAllConversations();
    }

    @GetMapping("/{id}")
    public Optional<Conversation> getConversationById(@PathVariable Long id) {
        return conversationService.getConversationById(id);
    }

    @PostMapping
    public Conversation createConversation(@RequestBody Conversation conversation) {
        return conversationService.saveConversation(conversation);
    }

    @GetMapping("/direct")
    public Conversation getOrCreateDirectConversation(@RequestParam Long user1, @RequestParam Long user2) {
        return conversationService.findDirectConversationBetweenUsers(user1, user2)
                .orElseGet(() -> {
                    Conversation conversation = new Conversation();
                    conversation.setGroup(false);
                    conversation.setName("Direct Chat");
                    Conversation saved = conversationService.saveConversation(conversation);
                    // Add both users as participants
                    ConversationParticipant p1 = new ConversationParticipant();
                    p1.setConversation(saved);
                    User u1 = new User();
                    u1.setId(user1);
                    p1.setUser(u1);
                    participantService.saveParticipant(p1);
                    ConversationParticipant p2 = new ConversationParticipant();
                    p2.setConversation(saved);
                    User u2 = new User();
                    u2.setId(user2);
                    p2.setUser(u2);
                    participantService.saveParticipant(p2);
                    return saved;
                });
    }
}
