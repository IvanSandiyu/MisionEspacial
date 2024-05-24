package com.example.MisionEspacialAPI.model;

import com.example.MisionEspacialAPI.DTO.InstructionRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
/*representan los datos que se intercambian entre la nave espacial y la Tierra.*/
@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private Date fecha;
    private String accion; // "Scan", "Collect Sample", "Deploy Rover"
    private String estado; // "Pending", "In Progress", "Completed"
    private String resultado; // Resultado de la instrucción, si aplica

    public Instruction(){
        this.fecha = new Date();
    }

    public Instruction(String accion)
    {

        this.accion = accion;
        this.fecha = new Date();
    }
    public Instruction(InstructionRequestDTO instructionRequestDTO){
        this.accion = instructionRequestDTO.getAccion();
        this.fecha = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
