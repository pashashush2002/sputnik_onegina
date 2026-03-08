package com.example.sputnik_onegina;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SatelliteServiceImpl {
    private final List<SatelliteFactory> factories;
    
    SatelliteServiceImpl() {
        factories = new ArrayList<>();
        factories.add(new CommunicationSatelliteFactory());
        factories.add(new ImagingSatelliteFactory());
    }

    public Satellite createSatellite(SatelliteParam param) throws SpaceOperationException {
        for (SatelliteFactory factory: factories) {
            if (factory.isSatelliteTypeSupported(param.getType())) {
                return factory.createSatelliteWithParameter(param);
            }
        }
        throw new SpaceOperationException("Factory for satellite type " + param.getType() + " not found");
    }
}
