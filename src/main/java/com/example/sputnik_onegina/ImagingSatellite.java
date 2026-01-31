package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

@Component
public class ImagingSatellite extends Satellite {
    private double resolution;
    private int photosTaken;

    ImagingSatellite(String name, double batteryLevel, double resolution) {
        super(name, batteryLevel);
        this.resolution = resolution;
        photosTaken = 0;
    }

    ImagingSatellite() {
        super();
        this.resolution = 0.;
        photosTaken = 0;
    }

    double getResolution() {
        return this.resolution;
    }
    int getPhotosTaken() {
        return  this.photosTaken;
    }
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
