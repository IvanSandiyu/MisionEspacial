package com.example.MisionEspacialAPI.repository;

import com.example.MisionEspacialAPI.model.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Los repositorios proporcionan acceso a la base de datos para almacenar y recuperar datos de telemetría e instrucciones.*/
@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, Integer> {
    //Telemetry sendTelemetry(Telemetry t);
}
