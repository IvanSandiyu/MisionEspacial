package com.example.MisionEspacialAPI.services;

import com.example.MisionEspacialAPI.model.Instruction;

import java.util.Map;

public interface InstructionService {
    void createInstruction(Instruction instruction);
    Integer insertInstruction(String accion, String estado, String resultado);
    Map<String, Object> getTelemetryData();

}
