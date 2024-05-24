package com.example.MisionEspacialAPI.DTO;

public class TelemetryRequestDTO {
    private String data;

    public TelemetryRequestDTO() {}

    public TelemetryRequestDTO(String data) {
        this.data = data;
    }

    // Getter y Setter
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
