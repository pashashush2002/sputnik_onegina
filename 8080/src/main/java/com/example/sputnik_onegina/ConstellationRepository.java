package com.example.sputnik_onegina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
@RequiredArgsConstructor
public class ConstellationRepository {
    private final Map<String, SatelliteConstellation> constellations;

    public ConstellationRepository() {
        constellations = new HashMap<>();
    }
    
    public void put(String key, SatelliteConstellation value) {
        constellations.put(key, value);
    }

    public SatelliteConstellation get(String key) {
        return constellations.get(key);
    }

    public ArrayList<SatelliteConstellation> getAllConstellations() {
        return new ArrayList<>(constellations.values());
    }
}
