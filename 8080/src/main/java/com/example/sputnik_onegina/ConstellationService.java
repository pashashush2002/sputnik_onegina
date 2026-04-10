package com.example.sputnik_onegina;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConstellationService {
    private final ConstellationRepository repository;

    void createAndSaveConstellation(String name) {
        SatelliteConstellation constellation = new SatelliteConstellation(name);
        repository.put(name, constellation);
        System.out.println("Спутниковая группировка " + name + " успешно сохранена!");
    }
    void addSatelliteToConstellation(String constellationName, Satellite satellite) {
        SatelliteConstellation constellation = repository.get(constellationName);
        if (constellation == null) {
            createAndSaveConstellation(constellationName);
            constellation = repository.get(constellationName);
        }
        constellation.addSatellite(satellite);
        // repository.put(constellationName, constellation);
    }
    void executeMission(String constellationName, String satelliteName, boolean isConstellation) throws SpaceOperationException {
        SatelliteConstellation constellation = repository.get(constellationName);
        if (isConstellation) {
             if (satelliteName != null)
                 throw new SpaceOperationException("Задано имя спутника для миссии группировки");
            constellation.executeAllMissions();
        }
        else {
            for (Satellite satellite: constellation.getSatellites()) {
                if (satellite.getName().equals(satelliteName)) {
                    satellite.performMission();
                }
            }
        }
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
    public String showOverview() { // Предполагаем, что все спутники в группировках
        String answer = "Информация о системе:\n";
        answer += "Количество группировок: " + repository.getAllConstellations().size() + '\n';
        for (SatelliteConstellation constellation: repository.getAllConstellations()) {
            answer += "Имя группировки: " + constellation.getConstellationName() + '\n';
            answer += "Количество спутников: " + constellation.getSatellites().size() + '\n';
            for (Satellite satellite: constellation.getSatellites()) {
                answer += "Имя спутника: " + satellite.getName();
                answer += "Заряд: " + satellite.getEnergy().getBatteryLevel();
                answer += "Активирован: " + (satellite.isActive()? "Да" : "Нет") + '\n';
            }
        }
        return answer;
    }
    public void deleteSatellite(String constellationName, String satelliteName) {
        SatelliteConstellation constellation = repository.get(constellationName);
        System.out.println("Спутник "+ satelliteName + " удалён из группировки " + constellationName + '!');
    }
}
