package com.example.MisionEspacialAPI.repository;

import com.example.MisionEspacialAPI.model.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, Integer> {
}
