package edu.upc.anse.backendanse.dto;

import java.math.BigDecimal;

public class VehiculoDTO {
    private Integer id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private BigDecimal capacidad;
    private String tipoCarga;
    private String estado;

    public VehiculoDTO() {}

    public VehiculoDTO(Integer id, String placa, String marca, String modelo, Integer anio, BigDecimal capacidad, String tipoCarga, String estado) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.capacidad = capacidad;
        this.tipoCarga = tipoCarga;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public BigDecimal getCapacidad() { return capacidad; }
    public void setCapacidad(BigDecimal capacidad) { this.capacidad = capacidad; }
    public String getTipoCarga() { return tipoCarga; }
    public void setTipoCarga(String tipoCarga) { this.tipoCarga = tipoCarga; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}