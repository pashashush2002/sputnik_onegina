package com.example.sputnik_onegina;

import java.util.ArrayList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddSatelliteRequest {
    @Getter
    private final String constellationName;
    @Getter
    private final ArrayList<SatelliteParam> satelliteParams;
}
