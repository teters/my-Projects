package com.example.PrimerProyectoTIC1.CentroDeportivoP;

import com.example.PrimerProyectoTIC1.EmpleadoP.Reserva;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Lob;
import java.util.List;

public class Actividad {

    //private DateTime horario;
    private List<Reserva> Reservas;
    private Long id;
    private CentroDeportivo1 centroDeportivo1;
    private String horario;
    private String dia;

    private Integer cupos;

    private String nombre;

    private Float precio;


    private String descripcion;

    private Boolean reserva;

    private String tipoActividad;

    private Long centro_deportivo_1_id;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<String> horarios;

    //private String img;


    public void setCentro_deportivo_1_id(Long centro_deportivo_1_id) {
        this.centro_deportivo_1_id = centro_deportivo_1_id;
    }

    public Actividad() {
    }

    public Actividad(List<Reserva> reservas, Long id, CentroDeportivo1 centroDeportivo1, String horario, String dia, Integer cupos, String nombre, Float precio, String descripcion, Boolean reserva, String tipoActividad, List<String> horarios) {
        Reservas = reservas;
        this.id = id;
        this.centroDeportivo1 = centroDeportivo1;
        this.horario = horario;
        this.dia = dia;
        this.cupos = cupos;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.reserva = reserva;
        this.tipoActividad = tipoActividad;
        this.horarios = horarios;
    }

    public List<Reserva> getReservas() {
        return Reservas;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public Long getCentro_deportivo_1_id(){
        return centro_deportivo_1_id;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public void setReservas(List<Reserva> reservas) {
        Reservas = reservas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CentroDeportivo1 getCentroDeportivo1() {
        return centroDeportivo1;
    }

    public void setCentroDeportivo1(CentroDeportivo1 centroDeportivo1) {
        this.centroDeportivo1 = centroDeportivo1;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getReserva() {
        return reserva;
    }

    public void setReserva(Boolean reserva) {
        this.reserva = reserva;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }
}

