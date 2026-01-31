package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

@Component
public class SatelliteState {
    private boolean isActive;
    private String name;

    SatelliteState(String name) {
        isActive = false;
        this.name = name;
    }

    SatelliteState() {
        isActive = false;
        this.name = null;
    }

    

    boolean isActive() {
        return isActive;
    }
    boolean activate(EnergySystem energy) {
        if (energy.getBatteryLevel()>0.2)
            isActive = true;
        if (isActive)
            System.out.println("Спутник " + name + " активирован!");
        else
            System.out.println("Спутник " + name + " не активирован (заряд: " + energy.getBatteryLevel() * 100 + "%)!");
        return isActive;
    };
    void deactivate() {
        if (isActive) {
            isActive = false;
            System.out.println("Спутник " + name + " деактивирован!");
        }
        else
            System.out.println("Спутник " + name + " уже деактивирован!");
    };
}
