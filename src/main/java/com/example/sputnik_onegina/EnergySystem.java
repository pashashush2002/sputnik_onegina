package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

@Component
public class EnergySystem {
    private double batteryLevel;

    EnergySystem(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    EnergySystem() {
        this.batteryLevel = 0.;
    }

    double getBatteryLevel() {
        return batteryLevel;
    }
    void consume(double Energy, SatelliteState satelliteState) {
        batteryLevel -= Energy;
        if (batteryLevel <= 0.2)
            satelliteState.deactivate();
    };
}
