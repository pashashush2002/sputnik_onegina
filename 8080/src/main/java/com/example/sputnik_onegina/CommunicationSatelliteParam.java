package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class CommunicationSatelliteParam extends SatelliteParam {
    @Getter
    private final double bandwith;

    CommunicationSatelliteParam() {
        super(SatelliteType.COMMUNICATION, null, 0.);
        this.bandwith = 0.;
    }

    CommunicationSatelliteParam(String name, double batteryLevel, double bandwith) {
        super(SatelliteType.COMMUNICATION, name, batteryLevel);
        this.bandwith = bandwith;
    }
}
