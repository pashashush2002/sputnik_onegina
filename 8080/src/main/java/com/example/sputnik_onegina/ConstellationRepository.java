package com.example.sputnik_onegina;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface ConstellationRepository extends JpaRepository<SatelliteConstellation, Long> {
    Optional<SatelliteConstellation> findByConstellationName(String name);
    boolean existsByConstellationName(String name);
    void deleteByConstellationName(String name);
}
