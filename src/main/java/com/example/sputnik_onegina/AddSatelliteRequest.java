package com.example.sputnik_onegina;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AddSatelliteRequest {
    @Getter
    private String constellationName;
    @Getter
    private ArrayList<Satellite> satellites;
}
