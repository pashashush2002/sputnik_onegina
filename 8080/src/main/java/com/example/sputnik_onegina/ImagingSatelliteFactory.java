package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

@Service
public class ImagingSatelliteFactory extends SatelliteFactory {
    @Override
    public Satellite createSatelliteWithParameter(SatelliteParam param) throws SpaceOperationException {
        if (param instanceof ImagingSatelliteParam imgParam) {
            return new ImagingSatellite(imgParam.getName(), imgParam.getBatteryLevel(), imgParam.getResolution());
        }
        else {
            throw new SpaceOperationException("Требуемый тип IMAGE");
        }
    }
    @Override
    public boolean isSatelliteTypeSupported(SatelliteType type) {
        return type == SatelliteType.IMAGE;
    }
}
