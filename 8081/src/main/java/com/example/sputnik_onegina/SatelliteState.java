package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class SatelliteState {
    private boolean isActive;
    private String name;

    public SatelliteState(String name) {
        isActive = false;
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }
    public boolean activate(EnergySystem energy) {
        if (energy.getBatteryLevel()>0.2)
            isActive = true;
        if (isActive)
            System.out.println("Спутник " + name + " активирован!");
        else
            System.out.println("Спутник " + name + " не активирован (заряд: " + energy.getBatteryLevel() * 100 + "%)!");
        return isActive;
    };
    public void deactivate() {
        if (isActive) {
            isActive = false;
            System.out.println("Спутник " + name + " деактивирован!");
        }
        else
            System.out.println("Спутник " + name + " уже деактивирован!");
    };
}
