package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

@Service
public class CommunicationSatelliteFactory extends SatelliteFactory {
    @Override
    public Satellite createSatelliteWithParameter(SatelliteParam param) throws SpaceOperationException {
        if (param instanceof CommunicationSatelliteParam comParam) {
            return new CommunicationSatellite(comParam.getName(), comParam.getBatteryLevel(), comParam.getBandwith());
        }
        else {
            throw new SpaceOperationException("Требуемый тип COMMUNICATION");
        }
    }
    @Override
    public boolean isSatelliteTypeSupported(SatelliteType type) {
        return type == SatelliteType.COMMUNICATION;
    }
}
