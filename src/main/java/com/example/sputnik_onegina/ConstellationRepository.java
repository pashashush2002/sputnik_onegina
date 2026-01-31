package com.example.sputnik_onegina;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ConstellationRepository {
    private Map<String, SatelliteConstellation> constellations;

    public ConstellationRepository() {
        constellations = new HashMap<>();
    }
    
    void put(String key, SatelliteConstellation value) {
        constellations.put(key, value);
    }

    SatelliteConstellation get(String key) {
        return constellations.get(key);
    }
}
