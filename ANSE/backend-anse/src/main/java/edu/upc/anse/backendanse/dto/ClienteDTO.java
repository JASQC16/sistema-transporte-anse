package edu.upc.anse.backendanse.dto;

public class ClienteDTO {
    private Integer id;
    private String razonSocial;
    private String rucDni;
    private String telefono;
    private String correo;
    private String direccion;

    public ClienteDTO() {}

    public ClienteDTO(Integer id, String razonSocial, String rucDni, String telefono, String correo, String direccion) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.rucDni = rucDni;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getRucDni() { return rucDni; }
    public void setRucDni(String rucDni) { this.rucDni = rucDni; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}