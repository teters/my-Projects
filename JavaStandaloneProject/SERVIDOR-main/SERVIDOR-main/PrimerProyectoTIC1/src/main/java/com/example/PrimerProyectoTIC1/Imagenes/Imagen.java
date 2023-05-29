package com.example.PrimerProyectoTIC1.Imagenes;

import javax.persistence.*;

@Entity
@Table(name= "imagenes")
public class Imagen {

    @Id
    @GeneratedValue
    Long id;

    @Lob
    String content;

    @Column
    String nombreActividad;





    public Imagen() {

    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String name) {
        this.nombreActividad = name;
    }
}