package com.example.sputnik_onegina;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfiguriedMissionScheduler {
    private final SpaceOperationClient spaceOperationClient;
    private final SpaceCenterServiceProperties properties;
    private final TaskScheduler taskScheduler;

    @PostConstruct
    public void init() {
        for (SpaceCenterServiceProperties.ConfiguriedMission config : properties.missions()) {
            MissionRequest request = new MissionRequest(
                    config.getConstellationName(),
                    config.getSatelliteName(),
                    config.getTargetType() == MissionTargetType.Constellation
            );
            taskScheduler.schedule(
                    () -> {
                        try {
                            spaceOperationClient.executeMission(request);
                            log.info("Выполнена миссия по расписанию: " + request);
                        }
                        catch (Exception e) {
                            log.error("Ошибка в запланированнной миссии:" + e.getMessage());
                        }
                    },
                    new CronTrigger(config.getCron())
            );
            log.info("Запланирована миссия " + request + " с cron " + config.getCron());
        }
    }
}
