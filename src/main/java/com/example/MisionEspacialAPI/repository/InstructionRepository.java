package com.example.MisionEspacialAPI.repository;

import com.example.MisionEspacialAPI.model.Instruction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*Los repositorios proporcionan acceso a la base de datos para almacenar y recuperar datos de telemetría e instrucciones.*/
public interface InstructionRepository extends JpaRepository<Instruction, Integer> {
    //Aca van las query necesarias

    @Transactional
    @Query(value = "INSERT INTO Instruction (fecha, accion, estado, resultado) VALUES (CURRENT_TIMESTAMP, :accion, :estado, :resultado)  RETURNING id", nativeQuery = true)
    Integer insertInstruction(@Param("accion") String accion, @Param("estado") String estado, @Param("resultado") String resultado);


}
