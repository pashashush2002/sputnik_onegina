package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class ImagingSatelliteParam extends SatelliteParam {
    @Getter
    private double resolition;
    
    ImagingSatelliteParam(String name, double batteryLevel, double resolition) {
        this.type = SatelliteType.IMAGE;
        this.name = name;
        this.batteryLevel = batteryLevel;
        this.resolition = resolition;
    }
}
