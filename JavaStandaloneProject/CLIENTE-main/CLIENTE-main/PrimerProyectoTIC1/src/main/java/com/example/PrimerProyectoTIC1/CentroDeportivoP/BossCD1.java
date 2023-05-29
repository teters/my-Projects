package com.example.PrimerProyectoTIC1.CentroDeportivoP;

public class BossCD1 {
    private CentroDeportivo1 centroDepor;
    private String mail;
    private String password;
    private Long telefono;
    private String nombre;
    private Long id;
    public BossCD1(CentroDeportivo1 centroDepor, String mail, String password, Long telefono, String nombre) {
        this.centroDepor = centroDepor;
        this.mail = mail;
        this.password = password;
        this.telefono = telefono;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BossCD1() {
    }

    public CentroDeportivo1 getCentroDepor() {
        return centroDepor;
    }

    public void setCentroDepor(CentroDeportivo1 centroDepor) {
        this.centroDepor = centroDepor;
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
}
