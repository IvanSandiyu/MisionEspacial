package com.example.MisionEspacialAPI.services;

import com.example.MisionEspacialAPI.model.Telemetry;
import com.example.MisionEspacialAPI.repository.InstructionRepository;
import com.example.MisionEspacialAPI.model.Instruction;
import com.example.MisionEspacialAPI.repository.TelemetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructionService {
    @Autowired
    private InstructionRepository instructionRepository;

    @Autowired
    private TelemetryRepository telemetryRepository;
    public boolean processInstruction(Instruction instruction) {
        // Guardar instrucción en la base de datos
        instruction.setEstado("Received");
        instructionRepository.save(instruction);

        boolean result;
        switch (instruction.getAccion()) {
            case "Scan":
                result = scanTerrain(instruction.getId());
                break;
            case "Collect Sample":
                result = collectSample(instruction.getId());
                break;
            case "Deploy Rover":
                result = deployRover(instruction.getId());
                break;
            default:
                System.out.println("Unknown instruction: " + instruction.getAccion());
                result = false;
                break;
        }

        // Actualizar el estado de la instrucción
        instruction.setEstado(result ? "Processed" : "Failed");
        instructionRepository.save(instruction);
        return result;
    }

    private boolean scanTerrain(Integer id) {
        String scanData = "{\"geography\": \"Mountainous\", \"temperature\": \"-20°C\", \"atmosphere\": {\"oxygen\": \"21%\", \"carbonDioxide\": \"0.04%\"}, \"radiation\": \"0.03 Sv\"}";
        // Guardar los datos de telemetría
        Telemetry telemetryData = new Telemetry(id, "Scan", scanData);
        telemetryRepository.save(telemetryData);
        return true;
    }

    private boolean collectSample(Integer id) {
        String sampleData = "{\"surfaceSample\": {\"composition\": \"Silicon dioxide, Iron oxide\", \"color\": \"Red\"}, \"depthSample\": {\"depth\": \"1 meter\", \"composition\": \"Silicon dioxide, Magnesium oxide\"}, \"rockSample\": {\"type\": \"Basalt\", \"composition\": \"Silicon dioxide, Aluminum oxide\"}}";

        // Guardar los datos de telemetría
        Telemetry telemetryData = new Telemetry(id, "Collect Sample", sampleData);
        telemetryRepository.save(telemetryData);
        return true;
    }

    private boolean deployRover(Integer id) {
        String roverData = "{\"areaType\": \"Crater\", \"coordinates\": {\"latitude\": \"-12.5\", \"longitude\": \"45.3\"}, \"mission\": \"Analyze impact composition\"}";

        // Guardar los datos de telemetría
        Telemetry telemetryData = new Telemetry(id, "Deploy Rover", roverData);
        telemetryRepository.save(telemetryData);
        return true;
    }
}