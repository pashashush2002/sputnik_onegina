package com.example.sputnik_onegina;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpaceOperationCenterService {
    private final ConstellationService constellationService;
    
    @Timed
    public void addSatellite(AddSatelliteRequest addSatelliteRequest) {
        for (SatelliteParam satelliteParam: addSatelliteRequest.getSatelliteParams()) {
            try {
                SatelliteServiceImpl satelliteService = new SatelliteServiceImpl();
                Satellite satellite = satelliteService.createSatellite(satelliteParam);
                constellationService.addSatelliteToConstellation(addSatelliteRequest.getConstellationName(), satellite);
            }
            catch (SpaceOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void executeMission(MissionRequest missionRequest) throws SpaceOperationException {
        constellationService.executeMission(missionRequest.getConstellationName(), missionRequest.getSatelliteName(), missionRequest.isConstellation());
    }
    public String getSystemOverview() {
        return constellationService.showOverview();
    }
    public void deleteSatellite(String constellationName, String satelliteName) {
        constellationService.deleteSatellite(constellationName, satelliteName);
    }
}
