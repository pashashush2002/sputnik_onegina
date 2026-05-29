package com.example.sputnik_onegina;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SatelliteIdRepository {
    @Getter
    private final Set<Long> activeIds = ConcurrentHashMap.newKeySet();

    public void add(Long satelliteId) {
        if (activeIds.add(satelliteId)) {
            log.info("Добавлен спутник с id = " + satelliteId);
        }
    }

    public void remove(Long satelliteId) {
        if (activeIds.remove(satelliteId)) {
            log.info("Спутник с id = " + satelliteId + " удалён!");
        }
    }
}
