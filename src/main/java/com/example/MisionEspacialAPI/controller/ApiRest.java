package com.example.MisionEspacialAPI.controller;
import com.example.MisionEspacialAPI.DTO.InstructionRequestDTO;
import com.example.MisionEspacialAPI.DTO.AuthenticateRequestDTO;
import com.example.MisionEspacialAPI.model.Authenticate;
import com.example.MisionEspacialAPI.model.Instruction;
import com.example.MisionEspacialAPI.repository.TelemetryRepository;
import com.example.MisionEspacialAPI.services.InstructionService;
import com.example.MisionEspacialAPI.services.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

import java.util.Map;

import static jakarta.xml.ws.Response.*;

@RestController
@RequestMapping(value="/api")
public class ApiRest {

    @Autowired
    TelemetryService telemetryService;

    @Autowired
    TelemetryRepository telemetryRepository;

    @Autowired
    private InstructionService instructionService;


    private Authenticate authenticationService;

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
        Map response = new HashMap();
        try {
            if (authenticationService.AuthenticateCredentials()) {
                Instruction ins = new Instruction(instructionRequestDTO);
                instructionService.createInstruction(ins);

                response.put("message", "Instruccion enviada correctamente");
                response.put("estado", ins.getEstado());
                response.put("resultado", ins.getResultado());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "ERROR");
                response.put("code", "500");
                response.put("message", "Autenticación fallida");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }

        } catch (Exception e) {
            response.put("message", "Error durante la autenticación o el procesamiento de la instrucción");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

            @GetMapping("/telemetry")
    public ResponseEntity<Map<String,Object>> getTelemetryData() {
                Map<String, Object> response = new HashMap<>();
                try {
                    if (authenticationService.AuthenticateCredentials()) {
                        Map<String, Object> telemetryData = instructionService.getTelemetryData();
                        return new ResponseEntity<>(telemetryData, HttpStatus.OK);
                    } else {
                        response.put("status", "ERROR");
                        response.put("code", "401");
                        response.put("message", "Autenticación fallida");
                        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                    }
                } catch (Exception e) {
                    response.put("status", "ERROR");
                    response.put("code", "500");
                    response.put("message", "Error al obtener los datos de telemetría: ");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

    @PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticateRequestDTO authenticateRequestDTO) {
        authenticationService = new Authenticate(authenticateRequestDTO);
        if (authenticationService.AuthenticateCredentials()) {
            return ResponseEntity.ok("Autenticación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida");
        }
    }

    //Excepciones en tiempo de serialización para web services
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleBadRequestException(HttpMessageNotReadableException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "BAD_REQUEST");
        response.put("code", "400");
        response.put("message", "Revise los campos de la solicitud: " );
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

}//Llave api





