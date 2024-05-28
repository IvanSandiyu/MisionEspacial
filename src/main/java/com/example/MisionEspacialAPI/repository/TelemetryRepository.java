package com.example.MisionEspacialAPI.repository;

import com.example.MisionEspacialAPI.model.Telemetry;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*Los repositorios proporcionan acceso a la base de datos para almacenar y recuperar datos de telemetría e instrucciones.*/
@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Telemetry (id,instruccion,resultado,accion_instruccion) VALUES (:id,:instruccion,:resultado,:estado)", nativeQuery = true)
    void createTelemetry(@Param("id") Integer id , @Param("instruccion") String instruccion,  @Param("resultado") String resultado,@Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Scan(id_scan,geografia, temperatura, oxigeno, carbono, atmosf, radiacion) VALUES (:id,:geografia, :temperatura, :oxigeno, :carbono, :atmos, :radiacion)", nativeQuery = true)
    void instructionScan(@Param("id") Integer id ,@Param("geografia") String geografia, @Param("temperatura") Integer temperatura, @Param("oxigeno") String oxigeno, @Param("carbono") String carbono, @Param("atmos") String atmos, @Param("radiacion") String radiacion);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CollectSample(id_collect,tipo_muestra, composicion, peso, temperatura) VALUES (:id,:tipoMuestra, :composicion, :peso, :temperatura)", nativeQuery = true)
    void instruccionCollectSample(@Param("id") Integer id ,@Param("tipoMuestra") String tipoMuestra, @Param("composicion") String composicion, @Param("peso") String peso, @Param("temperatura") String temperatura);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO DeployRover(id_rover,estado_rover, bateria, localizacion, tareas) VALUES (:id,:estadoDelRover, :batteryLevel, :location, :tareas)", nativeQuery = true)
    void instruccionDeployRover(@Param("id") Integer id ,@Param("estadoDelRover") String estadoDelRover, @Param("batteryLevel") String batteryLevel, @Param("location") String location, @Param("tareas") String tareas);

}
