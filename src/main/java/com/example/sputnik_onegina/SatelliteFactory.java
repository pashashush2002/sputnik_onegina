package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public abstract class SatelliteFactory {
    public abstract Satellite createSatellite(String name, double batteryLevel);
    public abstract Satellite createSatelliteWithParameter(String name, double batteryLevel, double parameter);
}
