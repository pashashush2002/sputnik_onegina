package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

@Service
public class ConstellationService {
    private ConstellationRepository repository;

    public ConstellationService() {
        repository = new ConstellationRepository();
    }

    void createAndSaveConstellation(String name) {
        SatelliteConstellation constellation = new SatelliteConstellation(name);
        repository.put(name, constellation);
        System.out.println("Спутниковая группировка " + name + " успешно сохранена!");
    }
    void addSatelliteToConstellation(String constellationName, Satellite satellite) {
        SatelliteConstellation constellation = repository.get(constellationName);
        constellation.addSatellite(satellite);
        // repository.put(constellationName, constellation);
    }
    void executeConstellationMission(String constellationName) {
        SatelliteConstellation constellation = repository.get(constellationName);
        constellation.executeAllMissions();
    }
    void activateAllSatellites(String constellationName) {
        SatelliteConstellation constellation = repository.get(constellationName);
        for (Satellite satellite: constellation.getSatellites()) {
            satellite.activate();
        }
    }
    void showConstellationStatus(String constellationName) {
        SatelliteConstellation constellation = repository.get(constellationName);
        System.out.println("Количество спутников в группировке: " + constellation.getSatellites().size());
        for (Satellite satellite: constellation.getSatellites()) {
            if (satellite.isActive()) {
                System.out.println("Спутник " + satellite.name + ": активирован!");
            }
            else {
                System.out.println("Спутник " + satellite.name + ": деактивирован!");
            }
        }
    }
}
