package edu.upc.anse.backendanse.dto;

public class RegistroRequest {
    private String usuario;
    private String contrasenia;
    private String rol;

    public RegistroRequest() {}

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}