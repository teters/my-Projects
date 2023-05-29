package com.example.PrimerProyectoTIC1.CentroDeportivoP;

import com.example.PrimerProyectoTIC1.EmpleadoP.Reserva;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class CentroDeportivo1 {
    private Long centro_id;

    private String nombre;

    private String direccion;

    private List<Actividad> actividads;

    private List<Reserva> reservas;



    public CentroDeportivo1() {
    }

    public CentroDeportivo1(Long id, String nombre, String direccion, List<Actividad> actividads) {
        this.centro_id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.actividads = actividads;

    }

    public CentroDeportivo1(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public CentroDeportivo1(Long id, String nombre, String direccion) {
        this.centro_id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        //this.actividads = actividads;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Long getId() {
        return centro_id;
    }

    public void setId(Long id) {
        this.centro_id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Actividad> getActividads() {
        return actividads;
    }

    public void setActividads(List<Actividad> actividads) {
        this.actividads = actividads;
    }


}
