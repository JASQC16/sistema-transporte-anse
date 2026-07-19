package edu.upc.anse.backendanse.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ServicioDTO {
    private Integer id;
    private Integer clienteId;
    private String clienteNombre;
    private Integer categoriaId;
    private String categoriaNombre;
    private Integer rutaId;
    private String rutaDescripcion;
    private Integer conductorId;
    private String conductorNombre;
    private Integer vehiculoId;
    private String vehiculoPlaca;
    private LocalDate fechaServicio;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
    private BigDecimal costo;
    private String observaciones;

    public ServicioDTO() {}

    public ServicioDTO(Integer id, Integer clienteId, String clienteNombre, Integer categoriaId, String categoriaNombre,
                       Integer rutaId, String rutaDescripcion, Integer conductorId, String conductorNombre,
                       Integer vehiculoId, String vehiculoPlaca, LocalDate fechaServicio, LocalTime horaInicio,
                       LocalTime horaFin, String estado, BigDecimal costo, String observaciones) {
        this.id = id;
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.rutaId = rutaId;
        this.rutaDescripcion = rutaDescripcion;
        this.conductorId = conductorId;
        this.conductorNombre = conductorNombre;
        this.vehiculoId = vehiculoId;
        this.vehiculoPlaca = vehiculoPlaca;
        this.fechaServicio = fechaServicio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.costo = costo;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
    public String getCategoriaNombre() { return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }
    public Integer getRutaId() { return rutaId; }
    public void setRutaId(Integer rutaId) { this.rutaId = rutaId; }
    public String getRutaDescripcion() { return rutaDescripcion; }
    public void setRutaDescripcion(String rutaDescripcion) { this.rutaDescripcion = rutaDescripcion; }
    public Integer getConductorId() { return conductorId; }
    public void setConductorId(Integer conductorId) { this.conductorId = conductorId; }
    public String getConductorNombre() { return conductorNombre; }
    public void setConductorNombre(String conductorNombre) { this.conductorNombre = conductorNombre; }
    public Integer getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Integer vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getVehiculoPlaca() { return vehiculoPlaca; }
    public void setVehiculoPlaca(String vehiculoPlaca) { this.vehiculoPlaca = vehiculoPlaca; }
    public LocalDate getFechaServicio() { return fechaServicio; }
    public void setFechaServicio(LocalDate fechaServicio) { this.fechaServicio = fechaServicio; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}