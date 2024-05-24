package com.example.MisionEspacialAPI.controller;
import com.example.MisionEspacialAPI.DTO.InstructionRequestDTO;
import com.example.MisionEspacialAPI.DTO.TelemetryRequestDTO;
import com.example.MisionEspacialAPI.model.Instruction;
import com.example.MisionEspacialAPI.model.Telemetry;
import com.example.MisionEspacialAPI.repository.TelemetryRepository;
import com.example.MisionEspacialAPI.services.InstructionService;
import com.example.MisionEspacialAPI.services.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/sendInstruction", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String,String>> sendInstruction(@RequestBody InstructionRequestDTO instructionRequestDTO) {
        Instruction ins = new Instruction(instructionRequestDTO);
        instructionService.createInstruction(ins);

        Map response = new HashMap();
        response.put("status", "OK");
        response.put("code", "200");
        response.put("message", "Instruccion enviada correctamente");
        response.put("estado", ins.getEstado());
        response.put("resultado", ins.getResultado());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/telemetry")
    public ResponseEntity<Map<String,Object>> getTelemetryData() {
        Map<String, Object> telemetryData = instructionService.getTelemetryData();
        return new ResponseEntity<>(telemetryData, HttpStatus.OK);
    }

   /* @PostMapping(value="/telemetry", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String,String>> receiveTelemetry(@RequestBody TelemetryRequestDTO telemetryRequestDTO) {
        Telemetry telemetry = new Telemetry(telemetryRequestDTO.getData());
        telemetryRepository.sendTelemetry(telemetry);

        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("code", "200");
        response.put("message", "Telemetría enviada correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/
    /*
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Verificación de credenciales (este es un ejemplo simple, se recomienda usar un método más seguro)
        if ("earth".equals(username) && "securepassword".equals(password)) {
            return ResponseEntity.ok("Autenticación exitosa");
        } else {
            return ResponseEntity.status(401).body("Autenticación fallida");
        }
    }
    /*
    //Excepciones en tiempo de serialización para web services
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleBadRequestException(HttpMessageNotReadableException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "BAD_REQUEST");
        response.put("code", "400");
        response.put("message", "Revise los campos de la solicitud: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleInternalServerErrorUnique(Exception ex, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "INTERNAL_SERVER_ERROR");
        response.put("code", "500");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleInternalServerError(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "INTERNAL_SERVER_ERROR");
        response.put("code", "500");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
*/
}//Llave api





