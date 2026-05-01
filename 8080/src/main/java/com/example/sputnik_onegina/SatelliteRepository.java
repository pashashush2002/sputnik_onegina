package com.example.sputnik_onegina;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
    List<Satellite> findByConstellationId(Long constellationId);
    Optional<Satellite> findByNameAndConstellationId(String name, Long constellationId);
}
