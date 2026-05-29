package com.example.sputnik_onegina;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConstellationService {
    private final ConstellationRepository constellationRepository;
    private final EnergyRepository energyRepository;
    private final SatelliteRepository satelliteRepository;
    private final KafkaService kafkaService;

    private static final String topic = "satellite-events";

    public void createAndSaveConstellation(String name) {
        SatelliteConstellation constellation = new SatelliteConstellation(name);
        constellationRepository.save(constellation);
        System.out.println("Спутниковая группировка " + name + " успешно сохранена!");
    }

    @Transactional(readOnly = true)
    public SatelliteConstellation findByConstellationName(String constellationName) {
        return constellationRepository.findByConstellationName(constellationName)
                .orElseThrow(() -> new RuntimeException("Группировка не найдена: " + constellationName));
    }

    public void addSatelliteToConstellation(String constellationName, Satellite satellite) {
        SatelliteConstellation constellation;
        try {
            constellation = findByConstellationName(constellationName);
        }
        catch(RuntimeException e) {
            constellation = new SatelliteConstellation(constellationName);
        }
        EnergySystem energy = satellite.getEnergy();
        energy = energyRepository.save(energy);
        satellite.setEnergy(energy);
        satellite = satelliteRepository.save(satellite);
        constellation.addSatellite(satellite);
        constellationRepository.save(constellation);
        kafkaService.sendToKafkaSatellite(
                topic,
                KafkaUtils.createEvent(satellite, SatelliteEvent.EventType.CREATED)
        );
    }
    public void executeMission(String constellationName, String satelliteName, boolean isConstellation) throws SpaceOperationException {
        SatelliteConstellation constellation = findByConstellationName(constellationName);
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
    public void activateAllSatellites(String constellationName) {
        SatelliteConstellation constellation = findByConstellationName(constellationName);
        for (Satellite satellite: constellation.getSatellites()) {
            satellite.activate();
        }
    }
    public void showConstellationStatus(String constellationName) {
        SatelliteConstellation constellation = findByConstellationName(constellationName);
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
        answer += "Количество группировок: " + constellationRepository.findAll().size() + '\n';
        for (SatelliteConstellation constellation: constellationRepository.findAll()) {
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

        SatelliteConstellation constellation = findByConstellationName(constellationName);
        Satellite satellite = satelliteRepository.findByNameAndConstellationId(satelliteName, constellation.getId()).get();
        satelliteRepository.delete(satellite);
        kafkaService.sendToKafkaSatellite(
                topic,
                KafkaUtils.createEvent(satellite, SatelliteEvent.EventType.DELETED)
        );
        System.out.println("Спутник "+ satelliteName + " удалён из группировки " + constellationName + '!');
    }
}
