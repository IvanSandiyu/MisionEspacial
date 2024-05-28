package com.example.MisionEspacialAPI.services;

import com.example.MisionEspacialAPI.model.Instruction;
import com.example.MisionEspacialAPI.repository.InstructionRepository;
import com.example.MisionEspacialAPI.repository.TelemetryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class InstructionServiceImpl implements InstructionService {
    @Autowired
    InstructionRepository instructionRepository;
    @Autowired
    TelemetryRepository telemetryRepository;

    @Autowired
    TelemetryServicesImpl telemetryServices;

    Map<String, Object> telemetryData ;

    @Override
    @Transactional
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

        Integer id = insertInstruction(instruction.getAccion(), instruction.getEstado(), instruction.getResultado());
        instruction.setId(id);
        instructionRepository.save(instruction);

        // Llamar al método receiveTelemetry y pasar el ID de la instrucción junto con los datos de telemetría
        telemetryServices.receiveTelemetry(id, getTelemetryData());
    }
    private void Scan(Instruction instruction) {
        instruction.setEstado("Completado");
        instruction.setResultado("Datos de escaneo enviados correctamente.");
        telemetryData = new HashMap<>();
        telemetryData.put("instruccion", instruction.getAccion());
        telemetryData.put("resultado", instruction.getResultado());
        telemetryData.put("estado", instruction.getEstado());
        telemetryData.put("geografia", "Mountainous");
        telemetryData.put("temperatura", -20);
        Map<String, String> atmosferaScan = new HashMap<>();
        atmosferaScan.put("oxigeno", "21%");
        atmosferaScan.put("dioxidoCarbono", "0.04%");
        telemetryData.put("atmosf", atmosferaScan);
        telemetryData.put("radiacion", "0.03 Sv");


    }

    private void CollectSample(Instruction instruction) {
        instruction.setEstado("Completado");
        instruction.setResultado("Muestras recogidas y enviadas a la Tierra.");
        telemetryData = new HashMap<>();
        telemetryData.put("instruccion", instruction.getAccion());
        telemetryData.put("resultado", instruction.getResultado());
        telemetryData.put("estado", instruction.getEstado());
        telemetryData.put("Tipo muestra", "Suelo");
        telemetryData.put("Composicion", "Minerales de silicato");
        telemetryData.put("Peso", "150 gramos");
        telemetryData.put("Temperatura", "-15°C");
    }

    private void DeployRover(Instruction instruction) {
        instruction.setEstado("Completado");
        instruction.setResultado("Rover desplegado exitosamente.");
        telemetryData = new HashMap<>();
        telemetryData.put("instruccion", instruction.getAccion());
        telemetryData.put("resultado", instruction.getResultado());
        telemetryData.put("estado", instruction.getEstado());
        telemetryData.put("Estado_Rover", "Desplegado");
        telemetryData.put("bateria", "85%");
        telemetryData.put("localizacion", "45.0°N 93.0°W");
        telemetryData.put("Tareas", "Exploracion");
    }
    @Override
    public Integer insertInstruction(String accion, String estado, String resultado) {
        return instructionRepository.insertInstruction(accion, estado, resultado);
        //telemetryRepository.createTelemetry(accion,resultado,estado);
    }

    @Override
    public Map<String, Object> getTelemetryData() {
        return telemetryData;
    }
}
