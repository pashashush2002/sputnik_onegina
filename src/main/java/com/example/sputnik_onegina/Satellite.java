package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public abstract class Satellite {
    @Getter
    protected String name;
    protected SatelliteState state;
    protected EnergySystem energy;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        state = new SatelliteState(name);
        energy = new EnergySystem(batteryLevel);
        System.out.println("Создан спутник " + this.name + " (заряд: " + batteryLevel * 100 + "%)!");
    }
    
    public boolean activate() {
        return state.activate(energy);
    }
    public boolean isActive() {
        return state.isActive();
    }    

    abstract protected void performMission();
}
