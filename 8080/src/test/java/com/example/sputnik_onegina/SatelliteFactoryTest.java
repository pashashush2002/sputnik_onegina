//package com.example.sputnik_onegina;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class SatelliteFactoryTest {
//    @Autowired
//    ConstellationRepository repository;
//    SatelliteServiceImpl service = new SatelliteServiceImpl();
//    Satellite comSat1, comSat2, imgSat1, imgSat2, imgSat3;
//    SatelliteConstellation satcon = new SatelliteConstellation("Орбита"); // Создание группировки
//    @Test
//    public void IntegrationTest() {
//        try {
//            comSat1 = service.createSatellite(new CommunicationSatelliteParam("Связь-1", 0.85, 500.));
//            comSat2 = service.createSatellite(new CommunicationSatelliteParam("Связь-2", 0.75, 1000.));
//            imgSat1 = service.createSatellite(new ImagingSatelliteParam("ДЗЗ-1", 0.92, 2.5));
//            imgSat2 = service.createSatellite(new ImagingSatelliteParam("ДЗЗ-2", 0.45, 1.));
//            imgSat3 = service.createSatellite(new ImagingSatelliteParam("ДЗЗ-3", 0.15, 0.5));
//        }
//        catch (SpaceOperationException e) {
//            System.out.println(e.getMessage());
//        }
//        assertEquals(0, repository.getAllConstellations().size());
//        // Добавление спутников в группировку
//        satcon.addSatellite(comSat1);
//        satcon.addSatellite(comSat2);
//        satcon.addSatellite(imgSat1);
//        satcon.addSatellite(imgSat2);
//        satcon.addSatellite(imgSat3);
//        repository.put(satcon.getConstellationName(), satcon);
//        assertEquals(1, repository.getAllConstellations().size());
//        assertEquals(5, repository.get(satcon.getConstellationName()).getSatellites().size());
//        for (Satellite satellite : repository.get(satcon.getConstellationName()).getSatellites()) {
//            assertFalse(satellite.isActive());
//        }
//        for (Satellite satellite : satcon.getSatellites()) {
//            satellite.activate(); // Активация спутников
//        }
//        for (Satellite satellite : repository.get(satcon.getConstellationName()).getSatellites()) {
//            if (satellite == imgSat3)
//                assertFalse(satellite.isActive());
//            else
//                assertTrue(satellite.isActive());
//        }
//        SatelliteConstellation satcon2 = repository.get(satcon.getConstellationName());
//        satcon2.executeAllMissions(); // Выполнение мисссий
//        assertEquals(0.8, comSat1.energy.getBatteryLevel(), 0.01);
//        assertEquals(0.7, comSat2.energy.getBatteryLevel(), 0.01);
//        assertEquals(0.84, imgSat1.energy.getBatteryLevel(), 0.01);
//        assertEquals(0.37, imgSat2.energy.getBatteryLevel(), 0.01);
//        assertEquals(0.15, imgSat3.energy.getBatteryLevel());
//        assertTrue(comSat1.isActive());
//        assertTrue(comSat2.isActive());
//        assertTrue(imgSat1.isActive());
//        assertTrue(imgSat2.isActive());
//        assertFalse(imgSat3.isActive());
//    }
//}
