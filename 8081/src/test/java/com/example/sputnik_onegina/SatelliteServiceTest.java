package com.example.sputnik_onegina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SatelliteServiceTest {
    @Autowired
    SatelliteServiceImpl service = new SatelliteServiceImpl();
    Satellite sat1, sat2;
    @Test
    public void IntegrationTest() {
        try {
            sat1 = service.createSatellite(new CommunicationSatelliteParam("Связь", 0.85, 500.));
            sat2 = service.createSatellite(new ImagingSatelliteParam("ДЗЗ", 0.92, 2.5));
        }
        catch (SpaceOperationException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(sat1 instanceof CommunicationSatellite);
        if (sat1 instanceof CommunicationSatellite comSat) {
            assertEquals("Связь", comSat.getName());
            assertEquals(0.85, comSat.getEnergy().getBatteryLevel());
            assertEquals(500., comSat.getBandwith());
        }
        assertTrue(sat2 instanceof ImagingSatellite);
        if (sat2 instanceof ImagingSatellite imgSat) {
            assertEquals("ДЗЗ", imgSat.getName());
            assertEquals(0.92, imgSat.getEnergy().getBatteryLevel());
            assertEquals(2.5, imgSat.getResolution());
        }
    }
}