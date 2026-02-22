package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CommunicationSatelliteFactory extends SatelliteFactory {
    @Override
    public Satellite createSatellite(String name, double batteryLevel) {
        return new CommunicationSatellite(name, batteryLevel, 0);
    }
    @Override
    public Satellite createSatelliteWithParameter(String name, double batteryLevel, double parameter) {
        return new CommunicationSatellite(name, batteryLevel, parameter);
    }
}
