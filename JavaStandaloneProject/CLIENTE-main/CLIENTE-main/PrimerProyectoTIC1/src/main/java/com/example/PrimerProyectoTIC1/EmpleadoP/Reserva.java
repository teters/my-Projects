package com.example.PrimerProyectoTIC1.EmpleadoP;

import com.example.PrimerProyectoTIC1.CentroDeportivoP.Actividad;

import java.time.LocalDate;

public class Reserva {
    private Long id;

    private Long empleadoId;

    private Long actividadId;

    private Long centroId;

    private String dia;

    private String fecha;

    private String hora;


    public Reserva(Long id, Long empleadoId, Long actividadId, Long centroId, String dia, String fecha, String hora) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.actividadId = actividadId;
        this.centroId = centroId;
        this.dia = dia;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Reserva() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public Long getCentroId() {
        return centroId;
    }

    public void setCentroId(Long centroId) {
        this.centroId = centroId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}