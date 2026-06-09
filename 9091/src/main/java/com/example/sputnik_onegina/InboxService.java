package com.example.sputnik_onegina;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class InboxService {
    private final InboxEventRepository inboxEventRepository;

    public InboxEvent saveToInbox(InboxEvent event) {
        return inboxEventRepository.save(event);
    }

    public boolean existsById(UUID eventID) {
        return inboxEventRepository.existsByEventId(eventID);
    }
}
