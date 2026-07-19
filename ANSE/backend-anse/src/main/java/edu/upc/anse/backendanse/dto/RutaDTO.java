package edu.upc.anse.backendanse.dto;

import java.math.BigDecimal;

public class RutaDTO {
    private Integer id;
    private String origen;
    private String destino;
    private BigDecimal distanciaKm;
    private String tiempoEstimado;

    public RutaDTO() {}

    public RutaDTO(Integer id, String origen, String destino, BigDecimal distanciaKm, String tiempoEstimado) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.tiempoEstimado = tiempoEstimado;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public BigDecimal getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(BigDecimal distanciaKm) { this.distanciaKm = distanciaKm; }
    public String getTiempoEstimado() { return tiempoEstimado; }
    public void setTiempoEstimado(String tiempoEstimado) { this.tiempoEstimado = tiempoEstimado; }
}