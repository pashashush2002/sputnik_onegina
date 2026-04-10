package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CommunicationSatellite extends Satellite {
    @Getter
    private double bandwith;

    public CommunicationSatellite(String name, double batteryLevel, double bandwith) {
        super(name, batteryLevel);
        this.bandwith = bandwith;
    }

    @Override
    public void performMission() {
        if (state.isActive()) {
            sendData(bandwith);
            energy.consume(0.05, state);
            System.out.println("Спутник " + name + " успешно передал данные со скоростью " + bandwith + "!");
        }
        else
            System.out.println("Спутник " + name + " не смог передать данные (заряд " + energy.getBatteryLevel() * 100 + "%), так как он неактивен!");
    }
    private void sendData(double data) {
        System.out.println("Отправлено " + data + " количество данных!");
    }
}
