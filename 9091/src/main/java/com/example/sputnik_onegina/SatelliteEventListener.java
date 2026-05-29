package com.example.sputnik_onegina;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class SatelliteEventListener {
    private static final String topic = "satellite-events";
    private final SatelliteIdRepository satelliteIdRepository;

    @KafkaListener(topics = topic, groupId = "telemetry-service-group")
    public void handleSatelliteEvent(ConsumerRecord<String, SatelliteEvent> record) {
        try {
            SatelliteEvent event = record.value();
            log.info("Получено сообщение: " + event);
            switch (event.eventType()) {
                case CREATED -> satelliteIdRepository.add(event.satelliteId());
                case DELETED -> satelliteIdRepository.remove(event.satelliteId());
            }
        }
        catch (Exception e) {
            log.error("Произошло исключение: " + e.getMessage());
        }
    }
}
