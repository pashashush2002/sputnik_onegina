package com.example.sputnik_onegina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class ConstellationRepository {
    private Map<String, SatelliteConstellation> constellations;

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
        ArrayList<SatelliteConstellation> answer = new ArrayList<>();
        for (SatelliteConstellation satelliteConstellation : constellations.values()) {
            answer.add(satelliteConstellation);
        }
        return answer;
    }
}
