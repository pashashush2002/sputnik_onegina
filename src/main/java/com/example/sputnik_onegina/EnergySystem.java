package com.example.sputnik_onegina;

import lombok.Builder;
import lombok.Getter;

@Builder
public class EnergySystem {
    @Getter
    private double batteryLevel;

    public void consume(double Energy, SatelliteState satelliteState) {
        batteryLevel -= Energy;
        if (batteryLevel <= 0.2)
            satelliteState.deactivate();
    };
}
