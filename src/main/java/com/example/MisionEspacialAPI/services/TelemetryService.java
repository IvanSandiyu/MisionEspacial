package com.example.MisionEspacialAPI.services;
import com.example.MisionEspacialAPI.model.Telemetry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;


public interface TelemetryService {
    void receiveTelemetry(Integer id,Map<String, Object> t);
    void createTelemetry(Telemetry telemetry);
    /*void insertScan();
    void insertDeploy();
    void insertCollect();*/



}
