package com.example.sputnik_onegina;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
	void main(String[] args) {
		try(ConfigurableApplicationContext context = SpringApplication.run(Main.class, args)) {
			System.out.println("Создание специализированных спутников:");
			SatelliteServiceImpl service = context.getBean(SatelliteServiceImpl.class);
			try {
				Satellite comSat1 = service.createSatellite(new CommunicationSatelliteParam("Связь-1", 0.85, 500.));
				Satellite comSat2 = service.createSatellite(new CommunicationSatelliteParam("Связь-2", 0.75, 1000.));
				Satellite imgSat1 = service.createSatellite(new ImagingSatelliteParam("ДЗЗ-1", 0.92, 2.5));
				Satellite imgSat2 = service.createSatellite(new ImagingSatelliteParam( "ДЗЗ-2", 0.45, 1.));
				Satellite imgSat3 = service.createSatellite(new ImagingSatelliteParam( "ДЗЗ-3", 0.15, 0.5));

				ConstellationService constellationService = context.getBean(ConstellationService.class);
				SpaceOperationCenterService spaceOperationCenterService = context.getBean(SpaceOperationCenterService.class, constellationService);
				constellationService.createAndSaveConstellation("Орбита-1");
				constellationService.createAndSaveConstellation("Орбита-2");
				System.out.println("Формирование спутниковой группировки:");
				ArrayList<Satellite> orbita1 = new ArrayList<>();
				ArrayList<Satellite> orbita2 = new ArrayList<>();
				orbita1.add(comSat1);
				orbita1.add(imgSat1);
				orbita1.add(imgSat2);
				orbita2.add(comSat2);
				orbita2.add(imgSat3);
				spaceOperationCenterService.addSatellite(new AddSatelliteRequest("Орбита-1", orbita1));
				spaceOperationCenterService.addSatellite(new AddSatelliteRequest("Орбита-2", orbita2));

				constellationService.showConstellationStatus("Орбита-1");

				System.out.println("Активация спутниковой группировки:");
				constellationService.activateAllSatellites("Орбита-1");

				spaceOperationCenterService.executeMission(new MissionRequest("Орбита-1"));

				constellationService.showConstellationStatus("Орбита-1");
			}
			catch (SpaceOperationException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
