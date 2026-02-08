package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class EnergySystem {
    @Getter
    private double batteryLevel;

    public void consume(double Energy, SatelliteState satelliteState) {
        batteryLevel -= Energy;
        if (batteryLevel <= 0.2)
            satelliteState.deactivate();
    };
}
