package com.example.MisionEspacialAPI.services;

import com.example.MisionEspacialAPI.model.Instruction;

import java.util.Map;

public interface InstructionService {
    public void createInstruction(Instruction instruction);
    void insertInstruction(String accion, String estado, String resultado);
    Map<String, Object> getTelemetryData();

}
