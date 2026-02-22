package com.example.sputnik_onegina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
	void main(String[] args) {
		try(ConfigurableApplicationContext context = SpringApplication.run(Main.class, args)) {
			ConstellationRepository constellationRepository = context.getBean(ConstellationRepository.class);
			System.out.println("Создание специализированных спутников:");
			SatelliteFactory satFac = context.getBean(CommunicationSatelliteFactory.class);
			Satellite comSat1 = satFac.createSatelliteWithParameter("Связь-1", 0.85, 500.);
			Satellite comSat2 = satFac.createSatelliteWithParameter("Связь-2", 0.75, 1000.);
			satFac = context.getBean(ImagingSatelliteFactory.class);
			Satellite imgSat1 = satFac.createSatelliteWithParameter("ДЗЗ-1", 0.92, 2.5);
			Satellite imgSat2 = satFac.createSatelliteWithParameter("ДЗЗ-2", 0.45, 1.);
			Satellite imgSat3 = satFac.createSatelliteWithParameter("ДЗЗ-3", 0.15, 0.5);

			SpaceOperationCenterService spaceOperationCenterService = context.getBean(SpaceOperationCenterService.class);
			spaceOperationCenterService.createAndSaveConstellation("Орбита-1");
			spaceOperationCenterService.createAndSaveConstellation("Орбита-2");
			System.out.println("Формирование спутниковой группировки:");
			spaceOperationCenterService.addSatelliteToConstellation("Орбита-1", comSat1);
			spaceOperationCenterService.addSatelliteToConstellation("Орбита-1", imgSat1);
			spaceOperationCenterService.addSatelliteToConstellation("Орбита-1", imgSat2);
			spaceOperationCenterService.addSatelliteToConstellation("Орбита-2", comSat2);
			spaceOperationCenterService.addSatelliteToConstellation("Орбита-2", imgSat3);

			spaceOperationCenterService.showConstellationStatus("Орбита-1");

			System.out.println("Активация спутниковой группировки:");
			spaceOperationCenterService.activateAllSatellites("Орбита-1");

			spaceOperationCenterService.executeConstellationMission("Орбита-1");

			spaceOperationCenterService.showConstellationStatus("Орбита-1");
		}
	}
}
