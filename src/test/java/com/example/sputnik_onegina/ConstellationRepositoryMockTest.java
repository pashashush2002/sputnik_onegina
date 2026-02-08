package com.example.sputnik_onegina;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Мок-тестирование")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ConstellationRepositoryMockTest {
    @Mock
    ConstellationRepository repository;
    Satellite comsat = new CommunicationSatellite("Спутник связи", 1., 100.);
    Satellite imgsat = new ImagingSatellite("Спутник ДЗЗ", 1., 100.);
    SatelliteConstellation constellation = new SatelliteConstellation("Группировка");
    @Test
    public void TestMockRep () {
        constellation.addSatellite(comsat);
        constellation.addSatellite(imgsat);
        repository.put(constellation.getConstellationName(), constellation);
        Mockito.verify(repository).put(constellation.getConstellationName(), constellation);
        ArrayList<SatelliteConstellation> constellation2 = repository.getAllConstellations();
        assertEquals(0, constellation2.size());

        Mockito.when(repository.getAllConstellations()).thenReturn(constellation2);
        ArrayList<SatelliteConstellation> constellation3 = repository.getAllConstellations();
        assertEquals(constellation2, constellation3);
    }
}
