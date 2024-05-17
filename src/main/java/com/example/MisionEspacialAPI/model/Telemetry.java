package com.example.MisionEspacialAPI.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Entity

public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Integer id;
    private String tipoEscaneo;
    private String data ;

    public Telemetry(){}

    public Telemetry(Integer id,String tipoEscaneo, String data){
        this.id=id;
        this.tipoEscaneo = tipoEscaneo;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoEscaneo() {
        return tipoEscaneo;
    }

    public void setTipoEscaneo(String tipoEscaneo) {
        this.tipoEscaneo = tipoEscaneo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
