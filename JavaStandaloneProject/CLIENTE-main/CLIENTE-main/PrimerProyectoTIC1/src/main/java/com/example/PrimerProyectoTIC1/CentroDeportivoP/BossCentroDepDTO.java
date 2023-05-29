package com.example.PrimerProyectoTIC1.CentroDeportivoP;

public class BossCentroDepDTO {
    private String centroDepNombre;
    private String mail;
    private String password;
    private Long telefono;
    private String nombre;


    public String getcentroDepNombre() {
        return centroDepNombre;
    }

    public void setcentroDepNombre(String centroDepNombre) {
        this.centroDepNombre = centroDepNombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
