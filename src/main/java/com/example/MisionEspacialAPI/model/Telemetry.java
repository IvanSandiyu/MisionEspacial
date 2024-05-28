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
    private String instruccion;
    private String resultado;
    private String accionInstruccion;

    public Telemetry(){}

    public Telemetry(Integer id,String instruccion,String resultado, String accion_instruccion){
        this.id = id;
        this.instruccion = instruccion;
        this.resultado = resultado;
        this.accionInstruccion = accion_instruccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getAccionInstruccion() {
        return accionInstruccion;
    }

    public void setAccionInstruccion(String accionInstruccion) {
        this.accionInstruccion = accionInstruccion;
    }
}
