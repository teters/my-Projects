package com.example.PrimerProyectoTIC1.CheckIn;

import com.example.PrimerProyectoTIC1.Actividades.Actividad;
import com.example.PrimerProyectoTIC1.User.Empleado;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="checkin")

public class CheckIn {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EmpleadoId")
    private Empleado empleado;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ActividadId")
    private Actividad actividad;

    @Id
    @GeneratedValue
    private Long id;
//    @Column
//    private LocalDate fecha;
    @Column
    private LocalDateTime hora;

    public CheckIn(Long id, LocalDateTime hora) {
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
