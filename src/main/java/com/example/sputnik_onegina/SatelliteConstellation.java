package com.example.sputnik_onegina;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class SatelliteConstellation {
    private String constellationName;
    private ArrayList<Satellite> satellites;

    SatelliteConstellation(String constellationName) {
        this.constellationName = constellationName;
        System.out.println("Создана спутниковая группировка " + this.constellationName + "!");
        satellites = new ArrayList<Satellite>();
    }

    SatelliteConstellation() {
        this.constellationName = null;
        satellites = new ArrayList<Satellite>();
    }

    void addSatellite(Satellite satellite) {
        satellites.add(satellite);
        System.out.println("В группировку " + constellationName + " добавлен спутник " + satellite.name + "!");
    }
    void executeAllMissions() {
        System.out.println("Выполнение миссий группировки " + constellationName + ":");
        for(Satellite satellite: satellites)
            satellite.performMission();
    }
    ArrayList<Satellite> getSatellites() {
        return satellites;
    }
}
