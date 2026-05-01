package com.example.sputnik_onegina;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "satellite_constellation")
@Component
@NoArgsConstructor
public class SatelliteConstellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Getter
    private String constellationName;

    @JsonManagedReference
    @OneToMany(mappedBy = "constellation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter
    private List<Satellite> satellites;

    public SatelliteConstellation(String constellationName) {
        this.constellationName = constellationName;
        System.out.println("Создана спутниковая группировка " + this.constellationName + "!");
        satellites = new ArrayList<>();
    }

    public void addSatellite(Satellite satellite) {
        satellite.setConstellation(this);
        satellites.add(satellite);
        System.out.println("В группировку " + constellationName + " добавлен спутник " + satellite.name + "!");
    }
    public void executeAllMissions() {
        System.out.println("Выполнение миссий группировки " + constellationName + ":");
        for(Satellite satellite: satellites)
            satellite.performMission();
    }
    public void deleteSatellite(String satelliteName) {
        for (Satellite satellite: satellites) {
            if (satellite.name.equals(satelliteName)) {
                satellites.remove(satellite);
                break; // комементируем, если все с таким именем
            }
        }
    }
}
