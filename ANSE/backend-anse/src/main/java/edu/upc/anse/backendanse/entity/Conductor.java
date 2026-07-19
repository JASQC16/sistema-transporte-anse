package edu.upc.anse.backendanse.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "conductores")
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private Integer id;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "licencia", nullable = false, unique = true, length = 30)
    private String licencia;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "categoria_licencia", nullable = false, length = 20)
    private String categoriaLicencia;

    @Column(name = "estado")
    private Boolean estado = true;

    public Conductor() {}

    public Conductor(String nombres, String apellidos, String licencia, String telefono, String categoriaLicencia) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.licencia = licencia;
        this.telefono = telefono;
        this.categoriaLicencia = categoriaLicencia;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCategoriaLicencia() { return categoriaLicencia; }
    public void setCategoriaLicencia(String categoriaLicencia) { this.categoriaLicencia = categoriaLicencia; }
    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conductor that = (Conductor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", licencia='" + licencia + '\'' +
                ", telefono='" + telefono + '\'' +
                ", categoriaLicencia='" + categoriaLicencia + '\'' +
                ", estado=" + estado +
                '}';
    }
}