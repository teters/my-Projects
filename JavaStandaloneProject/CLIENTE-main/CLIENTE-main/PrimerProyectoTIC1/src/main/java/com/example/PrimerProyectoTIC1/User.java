package com.example.PrimerProyectoTIC1;

public class User {
    private String mail;
    private String password;
    private Long telefono;

    public User(String mail, String password, Long telefono) {
        this.mail = mail;
        this.password= password;
        this.telefono = telefono;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }
}
