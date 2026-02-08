package com.example.sputnik_onegina;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Юнит-тестирование")
@SpringBootTest
public class ConstellationRepositoryUnitTest {
    Satellite comsat = new CommunicationSatellite("Спутник связи", 1., 100.);
    Satellite imgsat = new ImagingSatellite("Спутник ДЗЗ", 1., 100.);
    ConstellationRepository repository = new ConstellationRepository();
    SatelliteConstellation constellation = new SatelliteConstellation("Группировка");
    @Test
    public void TestGetSatCon() {
        constellation.addSatellite(comsat);
        constellation.addSatellite(imgsat);
        repository.put(constellation.getConstellationName(), constellation);
        SatelliteConstellation constellation2 = repository.get(constellation.getConstellationName());
        assertEquals(constellation, constellation2);
        ArrayList<Satellite> satellites = constellation2.getSatellites();
        assertEquals(2, satellites.size());
    }
}
