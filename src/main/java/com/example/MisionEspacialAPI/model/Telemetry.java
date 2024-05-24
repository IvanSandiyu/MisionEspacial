package com.example.MisionEspacialAPI.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
/*representan los datos que se intercambian entre la nave espacial y la Tierra.*/
@Entity
public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Integer id;
    private String data ;
    private String accionInstruccion;

    public Telemetry(){}

    public Telemetry(String accion){
        this.accionInstruccion = accion;
    }

    public String getAccionInstruccion() {
        return accionInstruccion;
    }

    public void setAccionInstruccion(String accionInstruccion) {
        this.accionInstruccion = accionInstruccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
