package com.example.sputnik_onegina;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
public class ImagingSatelliteParam extends SatelliteParam {
    @Getter
    private final double resolition;

    ImagingSatelliteParam() {
        super(SatelliteType.IMAGE, null, 0.);
        this.resolition = 0.;
    }

    ImagingSatelliteParam(String name, double batteryLevel, double resolution) {
        super(SatelliteType.IMAGE, name, batteryLevel);
        this.resolition = resolution;
    }
}
