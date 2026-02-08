package com.example.sputnik_onegina;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class SatelliteConstellation {
    @Getter
    private String constellationName;
    @Getter
    private ArrayList<Satellite> satellites;

    public SatelliteConstellation(String constellationName) {
        this.constellationName = constellationName;
        System.out.println("Создана спутниковая группировка " + this.constellationName + "!");
        satellites = new ArrayList<Satellite>();
    }

    public void addSatellite(Satellite satellite) {
        satellites.add(satellite);
        System.out.println("В группировку " + constellationName + " добавлен спутник " + satellite.name + "!");
    }
    public void executeAllMissions() {
        System.out.println("Выполнение миссий группировки " + constellationName + ":");
        for(Satellite satellite: satellites)
            satellite.performMission();
    }
}
