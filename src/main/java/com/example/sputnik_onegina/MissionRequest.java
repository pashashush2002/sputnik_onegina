package com.example.sputnik_onegina;

import java.util.ArrayList;

import lombok.Getter;

public class MissionRequest {
    @Getter
    private String constellationName;
    @Getter
    private boolean isConstellation;
    @Getter
    private ArrayList<Satellite> satellites;

    MissionRequest(String constellationName) {
        this.constellationName = constellationName;
        isConstellation = true;
        satellites = null;
    }
    MissionRequest(ArrayList<Satellite> satellites) {
        this.satellites = satellites;
        isConstellation = false;
        constellationName = null;
    }
}
