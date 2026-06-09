package com.example.sputnik_onegina;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "aggregate_id")
    private Long aggregateId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private SatelliteEvent.EventType eventType;

    @Column
    private String payload;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    public enum OutboxStatus { PENDING, SENT, FAILED }
}
