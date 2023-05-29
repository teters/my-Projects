package com.example.PrimerProyectoTIC1.EmpresaP;

public class BossEmpresa {
    private Empresa empresa;
    private String mail;
    private String password;
    private Long telefono;
    private String nombre;
    private Long id;
    public BossEmpresa(Empresa empresa, String mail, String password, Long telefono, String nombre) {
        this.empresa = empresa;
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

    public BossEmpresa() {
    }

    public Empresa getempresa() {
        return empresa;
    }

    public void setempresa(Empresa empresa) {
        this.empresa = empresa;
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
