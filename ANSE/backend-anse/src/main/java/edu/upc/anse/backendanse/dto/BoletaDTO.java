package edu.upc.anse.backendanse.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BoletaDTO {
    private Integer id;
    private Integer servicioId;
    private String numero;
    private LocalDate fechaEmision;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal total;
    private String pdf;

    public BoletaDTO() {}

    public BoletaDTO(Integer id, Integer servicioId, String numero, LocalDate fechaEmision, BigDecimal subtotal, BigDecimal igv, BigDecimal total, String pdf) {
        this.id = id;
        this.servicioId = servicioId;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.pdf = pdf;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getServicioId() { return servicioId; }
    public void setServicioId(Integer servicioId) { this.servicioId = servicioId; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getIgv() { return igv; }
    public void setIgv(BigDecimal igv) { this.igv = igv; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getPdf() { return pdf; }
    public void setPdf(String pdf) { this.pdf = pdf; }
}