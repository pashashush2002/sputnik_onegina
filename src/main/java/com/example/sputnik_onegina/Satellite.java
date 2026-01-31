package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

@Component
public abstract class Satellite {
    protected String name;
    protected SatelliteState state;
    protected EnergySystem energy;

    Satellite(String name, double batteryLevel) {
        this.name = name;
        state = new SatelliteState(name);
        energy = new EnergySystem(batteryLevel);
        System.out.println("Создан спутник " + this.name + " (заряд: " + batteryLevel * 100 + "%)!");
    }

    Satellite() {
        this.name = null;
        state = new SatelliteState();
        energy = new EnergySystem();
    }
    
    boolean activate() {
        return state.activate(energy);
    }
    boolean isActive() {
        return state.isActive();
    }    

    abstract protected void performMission();
}
