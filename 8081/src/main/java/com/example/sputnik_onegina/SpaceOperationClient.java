package com.example.sputnik_onegina;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SpaceOperationClient {
    private RestClient spaceOperationRestClient;

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
        .uri("/add-missions")
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .toBodilessEntity();
        log.info("Миссия выполнена!");
    }

    public String getSystemOverview() {
        return spaceOperationRestClient.get()
        .uri("/overview")
        .retrieve()
        .body(String.class);
    }

    public void removeSatellite(String constellationName, String satelliteName) {
        spaceOperationRestClient.delete()
        .uri("/constellations/{constellationName}/satellites/{satellitesName}",
            constellationName, satelliteName
        )
        .retrieve()
        .toBodilessEntity();
        log.info("Спутник выведен из эксплуатации");
    }
}
