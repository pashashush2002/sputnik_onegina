package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SpaceOperationCenterService {
    ConstellationService constellationService;
    
    @Timed
    public void addSatellite(AddSatelliteRequest addSatelliteRequest) {
        for (Satellite satellite: addSatelliteRequest.getSatellites()) {
            constellationService.addSatelliteToConstellation(addSatelliteRequest.getConstellationName(), satellite);
        }
    }
    public void executeMission(MissionRequest missionRequest) {
        if (missionRequest.isConstellation()) {
            constellationService.executeConstellationMission(missionRequest.getConstellationName());
        }
        else {
            for (Satellite satellite: missionRequest.getSatellites()) {
                satellite.performMission();
            }
        }
    }
}
