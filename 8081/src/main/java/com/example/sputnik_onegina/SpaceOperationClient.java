package com.example.sputnik_onegina;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpaceOperationClient {
    private final RestClient spaceOperationRestClient;

    public void AddSatellite(AddSatelliteRequest request) {
        spaceOperationRestClient.post()
        .uri("/add-satellites")
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .toBodilessEntity();
        log.info("Спутник добавлен в группировку!");
    }

    public void executeMission(MissionRequest request) {
        spaceOperationRestClient.post()
        .uri("/missions")
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .toBodilessEntity();
        log.info("Миссия выполнена!");
    }

    public String getSystemOverview() {
        return spaceOperationRestClient.post()
        .uri("/overview")
        .retrieve()
        .body(String.class);
    }

    public void removeSatellite(String constellationName, String satelliteName) {
        spaceOperationRestClient.post()
        .uri("/constellations/{constellationName}/satellites/{satellitesName}",
            constellationName, satelliteName
        )
        .retrieve()
        .toBodilessEntity();
        log.info("Спутник выведен из эксплуатации");
    }
}
