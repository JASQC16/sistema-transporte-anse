package edu.upc.anse.backendanse.dto;

public class ConductorDTO {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String licencia;
    private String telefono;
    private String categoriaLicencia;
    private Boolean estado;

    public ConductorDTO() {}

    public ConductorDTO(Integer id, String nombres, String apellidos, String licencia, String telefono, String categoriaLicencia, Boolean estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.licencia = licencia;
        this.telefono = telefono;
        this.categoriaLicencia = categoriaLicencia;
        this.estado = estado;
    }

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
}