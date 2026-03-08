package com.example.sputnik_onegina;

import lombok.Getter;

public abstract class SatelliteParam {
    @Getter
    protected SatelliteType type;
    @Getter
    protected String name;
    @Getter
    protected double batteryLevel;
}
