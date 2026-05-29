package com.example.sputnik_onegina;

import lombok.experimental.UtilityClass;

import java.time.Instant;

@UtilityClass
public class KafkaUtils {
    public static SatelliteEvent createEvent(Satellite satellite, SatelliteEvent.EventType eventType) {
        return new SatelliteEvent(
                satellite.getId(),
                satellite.getName(),
                eventType,
                Instant.now()
        );
    }
}
