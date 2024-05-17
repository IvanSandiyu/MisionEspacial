package com.example.MisionEspacialAPI.controller;
import com.example.MisionEspacialAPI.model.Instruction;
import com.example.MisionEspacialAPI.repository.TelemetryRepository;
import com.example.MisionEspacialAPI.model.Telemetry;
import com.example.MisionEspacialAPI.services.InstructionService;
import com.example.MisionEspacialAPI.services.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api")
public class ApiRest {

    @Autowired
    TelemetryService telemetryService;

    @Autowired
    TelemetryRepository telemetryRepository;

    @Autowired
    private InstructionService instructionService;

    @GetMapping("/status")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("code", "200");
        response.put("message", "online");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hola Mundo";
    }


    @PostMapping("/sendInstruction")
    public ResponseEntity<String> sendInstruction(@RequestBody Instruction instruction) {
        boolean result = instructionService.processInstruction(instruction);
        return result ? ResponseEntity.ok("Instruction processed successfully")
                : ResponseEntity.status(500).body("Failed to process instruction");
    }

}//Llave api





