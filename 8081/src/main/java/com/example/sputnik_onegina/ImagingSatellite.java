package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ImagingSatellite extends Satellite {
    @Getter
    private double resolution;
    @Getter
    private int photosTaken;

    public ImagingSatellite(String name, double batteryLevel, double resolution) {
        super(name, batteryLevel);
        this.resolution = resolution;
        photosTaken = 0;
    }

    @Override
    public void performMission() {
        if (state.isActive()) {
            takePhoto();
            energy.consume(0.08, state);
            System.out.println("Спутник " + name + " успешно сделал снимок №" + photosTaken + " с разрешением " + resolution + "!");
        }
        else System.out.println("Спутник " + name + " не сделал снимок, так как он неактивен (заряд: " + energy.getBatteryLevel() * 100 + "%)!");
    }
    private void takePhoto() {
        if (state.isActive())
            photosTaken++;
    }
}
