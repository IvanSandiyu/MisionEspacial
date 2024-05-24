package com.example.MisionEspacialAPI.services;

import com.example.MisionEspacialAPI.model.Instruction;
import com.example.MisionEspacialAPI.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class InstructionServiceImpl implements InstructionService {
    @Autowired
    InstructionRepository instructionRepository;
    Map<String, Object> telemetryData = new HashMap<>();

    @Override
    public void createInstruction(Instruction instruction){

        switch (instruction.getAccion()) {
            case "Scan":

                Scan(instruction);
                break;
            case "Collect Sample":

                CollectSample(instruction);
                break;
            case "Deploy Rover":

                DeployRover(instruction);
                break;
            default:
                instruction.setEstado("Intruccion no válida");
                instruction.setResultado("Error.");
                //throw new IllegalArgumentException("Acción desconocida: " + instruction.getAccion());
        }
        instructionRepository.save(instruction);
        insertInstruction(instruction.getAccion(),instruction.getEstado(),instruction.getResultado());
    }
    private void Scan(Instruction instruction) {
        // Implementa la lógica para el escaneo del terreno
        instruction.setEstado("Completado");
        instruction.setResultado("Datos de escaneo enviados correctamente.");
        telemetryData.put("geography", "Mountainous");
        telemetryData.put("temperature", "-20°C");
        Map<String, String> atmosferaScan = new HashMap<>();
        atmosferaScan.put("oxygen", "21%");
        atmosferaScan.put("carbonDioxide", "0.04%");
        telemetryData.put("atmosphere", atmosferaScan);
        telemetryData.put("radiation", "0.03 Sv");

    }

    private void CollectSample(Instruction instruction) {
        // Implementa la lógica para recoger muestras
        instruction.setEstado("Completado");
        instruction.setResultado("Muestras recogidas y enviadas a la Tierra.");
        telemetryData.put("sampleType", "Soil");
        telemetryData.put("composition", "Silicate minerals");
        telemetryData.put("weight", "150 grams");
        telemetryData.put("temperature", "-15°C");
    }

    private void DeployRover(Instruction instruction) {
        // Implementa la lógica para desplegar el rover
        instruction.setEstado("Completado");
        instruction.setResultado("Rover desplegado exitosamente.");
        telemetryData.put("Estado del Rover", "Desplegado");
        telemetryData.put("batteryLevel", "85%");
        telemetryData.put("location", "45.0°N 93.0°W");
        telemetryData.put("Tareas", "Exploracion");
    }
    @Override
    public void insertInstruction(String accion, String estado, String resultado) {
        instructionRepository.insertInstruction(accion, estado, resultado);
    }
    public Map<String, Object> getTelemetryData() {
        return telemetryData;
    }
}
