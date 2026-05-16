package com.example.sputnik_onegina;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("app.space-center-service")
public record SpaceCenterServiceProperties(String url, List<ConfiguriedMission> missions) {
    @Data
    public static class ConfiguriedMission {
        private MissionTargetType targetType;
        private String constellationName;
        private String satelliteName;
        private String cron;
    }
}
