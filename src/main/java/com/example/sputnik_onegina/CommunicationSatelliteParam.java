package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CommunicationSatelliteParam extends SatelliteParam {
    @Getter
    private double bandwith;

    public CommunicationSatelliteParam(String name, double batteryLevel, double bandwith) {
        this.type = SatelliteType.COMMUNICATION;
        this.name = name;
        this.batteryLevel = batteryLevel;
        this.bandwith = bandwith;
    }
}
