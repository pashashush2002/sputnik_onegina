package com.example.sputnik_onegina;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "energy_system")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergySystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "battery_level", nullable = false)
    private double batteryLevel;

    public void consume(double Energy, SatelliteState satelliteState) {
        batteryLevel -= Energy;
        if (batteryLevel <= 0.2)
            satelliteState.deactivate();
    };
}
