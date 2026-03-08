package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

@Service
public abstract class SatelliteFactory {
    public abstract Satellite createSatelliteWithParameter(SatelliteParam param) throws SpaceOperationException;
    public abstract boolean isSatelliteTypeSupported(SatelliteType type);
}
