package com.example.MisionEspacialAPI.repository;

import com.example.MisionEspacialAPI.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {
    //Aca van las query necesarias
    /*Ej
    *
    *@Query("select per from Persona per where per.dni = :dniPaciente")
    * Paciente findPacienteByDni(Integer dniPaciente);
    *
    * */
}
