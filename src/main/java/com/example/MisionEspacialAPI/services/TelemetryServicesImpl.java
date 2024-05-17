package com.example.MisionEspacialAPI.services;
import com.example.MisionEspacialAPI.model.Telemetry;
import com.example.MisionEspacialAPI.repository.TelemetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelemetryServicesImpl implements TelemetryService {
    @Autowired
    TelemetryRepository telemetryRepository;

    public void showTelemetry(Telemetry t) {
        telemetryRepository.save(t);
    }


}
