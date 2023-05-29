package com.example.PrimerProyectoTIC1.CentrosDeportivos;

import com.example.PrimerProyectoTIC1.Actividades.Actividad;
import com.example.PrimerProyectoTIC1.Reserva.Reserva;
import com.example.PrimerProyectoTIC1.User.Empleado;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="centrodeportivo")
public class CentroDeportivo1 {

    @OneToMany
    @JoinColumn(name="CentroId")
    List<Reserva> reservas;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long centro_id;
    @Column
    private String nombre;
    @Column(unique = true)
    private String direccion;

    @OneToMany(mappedBy = "centroDeportivo1")
    //@JoinColumn(name = "centroDeportivoID")
    @JsonManagedReference
    private List<Actividad> actividads=new ArrayList<>();

    public List<Actividad> getActividads() {
        return actividads;
    }

    public void setActividads(List<Actividad> actividads) {
        this.actividads = actividads;
    }

    public CentroDeportivo1() {
    }

    public CentroDeportivo1(Long id, String nombre, String direccion) {
        this.centro_id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Long getId() {
        return centro_id;
    }

    public void setId(Long id) {
        this.centro_id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
