package com.example.PrimerProyectoTIC1.AdminP;

public class Admin {
    private String mail;
    private String contrasena;

    public Admin(String mail, String contrasena) {
        this.mail = mail;
        this.contrasena = contrasena;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
