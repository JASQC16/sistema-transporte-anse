package edu.upc.anse.backendanse.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "rutas")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Integer id;

    @Column(name = "origen", nullable = false, length = 150)
    private String origen;

    @Column(name = "destino", nullable = false, length = 150)
    private String destino;

    @Column(name = "distancia_km", nullable = false, precision = 8, scale = 2)
    private BigDecimal distanciaKm;

    @Column(name = "tiempo_estimado", nullable = false, length = 30)
    private String tiempoEstimado;

    public Ruta() {}

    public Ruta(String origen, String destino, BigDecimal distanciaKm, String tiempoEstimado) {
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.tiempoEstimado = tiempoEstimado;
    }

    // Getters y Setters
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruta ruta = (Ruta) o;
        return Objects.equals(id, ruta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "id=" + id +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", distanciaKm=" + distanciaKm +
                ", tiempoEstimado='" + tiempoEstimado + '\'' +
                '}';
    }
}