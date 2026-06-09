package com.example.sputnik_onegina;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inbox")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InboxEvent {
    @Id
    @Column(name = "event_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;

    @Column(name = "aggregate_id")
    private Long aggregateId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private SatelliteEvent.EventType eventType;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;
}
