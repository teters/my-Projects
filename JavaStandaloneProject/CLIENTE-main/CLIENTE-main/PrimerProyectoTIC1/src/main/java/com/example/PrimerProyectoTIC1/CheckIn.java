package com.example.PrimerProyectoTIC1;

import com.example.PrimerProyectoTIC1.CentroDeportivoP.Actividad;
import com.example.PrimerProyectoTIC1.EmpleadoP.Empleado;

import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.List;

public class CheckIn {
    private Empleado empleado;
    private Actividad actividad;
    private Long id;
    private LocalDateTime hora;

    public CheckIn(Empleado empleado, Actividad actividad, Long id, LocalDateTime hora) {
        this.empleado = empleado;
        this.actividad = actividad;
        this.id = id;
        this.hora = hora;
    }

    public CheckIn() {
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }
}
