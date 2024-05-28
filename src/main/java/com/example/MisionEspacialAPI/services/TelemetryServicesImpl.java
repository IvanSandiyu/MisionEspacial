package com.example.MisionEspacialAPI.services;
import com.example.MisionEspacialAPI.model.Telemetry;
import com.example.MisionEspacialAPI.repository.TelemetryRepository;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.Optional;

@Service
public class TelemetryServicesImpl implements TelemetryService {

    @Autowired
    TelemetryRepository telemetryRepository;

    @Override
    public void receiveTelemetry(Integer id,Map<String, Object> t) {
        // Guardar el objeto Telemetry en el repositorio
        String instruccion = (String) t.get("instruccion");
        String resultado = (String) t.get("resultado");
        String estado = (String) t.get("estado");

        Telemetry telemetry = new Telemetry();
        telemetry.setId(id);
        telemetry.setInstruccion(instruccion);
        telemetry.setResultado(resultado);
        telemetry.setAccionInstruccion(estado);

        Telemetry savedTelemetry = telemetryRepository.save(telemetry);
        Integer telemetryId = savedTelemetry.getId();

        switch (instruccion) {
            case "Scan":
                String geografia = (String) t.get("geografia");
                Integer temperatura = (Integer) t.get("temperatura");
                String oxigeno = (String) ((Map<String, String>) t.get("atmosf")).get("oxigeno");
                String carbono = (String) ((Map<String, String>) t.get("atmosf")).get("dioxidoCarbono");
                String radiacion = (String) t.get("radiacion");
                telemetryRepository.instructionScan(telemetryId, geografia, temperatura, oxigeno, carbono, "Atmosfera", radiacion);
                break;
            case "Collect Sample":
                String tipoMuestra = (String) t.get("Tipo muestra");
                String composicion = (String) t.get("Composicion");
                String peso = (String) t.get("Peso");
                String temp = (String) t.get("Temperatura");
                telemetryRepository.instruccionCollectSample(telemetryId, tipoMuestra, composicion, peso, temp);
                break;
            case "Deploy Rover":
                String estadoRover = (String) t.get("Estado_Rover");
                String bateria = (String) t.get("bateria");
                String localizacion = (String) t.get("localizacion");
                String tareas = (String) t.get("Tareas");
                telemetryRepository.instruccionDeployRover(telemetryId, estadoRover, bateria, localizacion, tareas);
                break;
            default:
                // Si la instrucción no es reconocida, no se hace nada
                break;
        }


    }
    @Override
    public void createTelemetry(Telemetry t){
        telemetryRepository.createTelemetry(t.getId(), t.getInstruccion(),t.getResultado(),t.getAccionInstruccion());
    }


}
