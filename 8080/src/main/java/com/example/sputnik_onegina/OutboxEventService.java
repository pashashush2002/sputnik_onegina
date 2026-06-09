package com.example.sputnik_onegina;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OutboxEventService {
    private final KafkaService kafkaService;
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    private static final String topic = "satellite-events";
    private static final int batch_size = 50;

    public void publishToOutbox(SatelliteEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);

            OutboxEvent outboxEvent = OutboxEvent.builder()
                    .aggregateId(event.satelliteId())
                    .eventType(event.eventType())
                    .payload(payload)
                    .createdAt(LocalDateTime.now())
                    .status(OutboxEvent.OutboxStatus.PENDING)
                    .build();
            outboxEventRepository.save(outboxEvent);
        }
        catch (JacksonException e) {
            String error = "Ошибка сериализации JSON: " + e.getMessage();
            log.error(error);
            throw new RuntimeException(error);
        }
    }

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishPendingEvents() {
        List<OutboxEvent> pending = outboxEventRepository.findByStatusOrderByCreatedByAsc(
                OutboxEvent.OutboxStatus.PENDING,
                PageRequest.of(0, batch_size));
        if(pending.isEmpty()) {
            return;
        }
        for (OutboxEvent event: pending) {
            try {
                SatelliteEvent satelliteEvent = objectMapper.readValue(event.getPayload(), SatelliteEvent.class);
                kafkaService.sendToKafkaSatellite(
                        topic,
                        satelliteEvent
                );
                outboxEventRepository.updateStatus(event.getId(), OutboxEvent.OutboxStatus.SENT);
            }
            catch (JacksonException e) {
                String error = "Ошибка десериализации: " + e.getMessage();
                log.error(error);
                outboxEventRepository.updateStatus(event.getId(), OutboxEvent.OutboxStatus.FAILED);
                throw new RuntimeException(error);
            }
        }
    }
}
