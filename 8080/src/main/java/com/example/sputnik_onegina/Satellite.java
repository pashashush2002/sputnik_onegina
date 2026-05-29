package com.example.sputnik_onegina;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "satellite")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "satellite_type", discriminatorType = DiscriminatorType.STRING)
@Component
@NoArgsConstructor
public abstract class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    protected String name;

    @Getter
    @Setter
    @Column
    protected double innerTemperature;

    @Getter
    @Setter
    @Column
    protected double outerTemperature;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constellation_id")
    @Setter
    SatelliteConstellation constellation;

    @Embedded
    protected SatelliteState state;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "energy_id", unique = true)
    @Getter
    @Setter
    protected EnergySystem energy;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        state = new SatelliteState(name);
        energy = EnergySystem.builder().batteryLevel(batteryLevel).build();
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
