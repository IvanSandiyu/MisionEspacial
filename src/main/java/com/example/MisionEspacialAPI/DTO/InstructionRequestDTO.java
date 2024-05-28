package com.example.MisionEspacialAPI.DTO;

public class InstructionRequestDTO {
    private String accion;
    public InstructionRequestDTO() {}

    public InstructionRequestDTO(String accion) {
        this.accion = accion;
    }
    public String getAccion() {
        return accion;
    }
    public void setAccion(String accion) {
        this.accion = accion;
    }



}
