package edu.upc.anse.backendanse.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MantenimientoDTO {
    private Integer id;
    private Integer vehiculoId;
    private String vehiculoPlaca;
    private LocalDate fecha;
    private String tipo;
    private String descripcion;
    private BigDecimal costo;
    private Integer kilometraje;

    public MantenimientoDTO() {}

    public MantenimientoDTO(Integer id, Integer vehiculoId, String vehiculoPlaca, LocalDate fecha, String tipo, String descripcion, BigDecimal costo, Integer kilometraje) {
        this.id = id;
        this.vehiculoId = vehiculoId;
        this.vehiculoPlaca = vehiculoPlaca;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.kilometraje = kilometraje;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Integer vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getVehiculoPlaca() { return vehiculoPlaca; }
    public void setVehiculoPlaca(String vehiculoPlaca) { this.vehiculoPlaca = vehiculoPlaca; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
    public Integer getKilometraje() { return kilometraje; }
    public void setKilometraje(Integer kilometraje) { this.kilometraje = kilometraje; }
}