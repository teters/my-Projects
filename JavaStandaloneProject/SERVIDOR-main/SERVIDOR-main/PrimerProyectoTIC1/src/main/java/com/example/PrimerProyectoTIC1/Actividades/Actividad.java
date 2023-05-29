package com.example.PrimerProyectoTIC1.Actividades;

import com.example.PrimerProyectoTIC1.CentrosDeportivos.CentroDeportivo1;
import com.example.PrimerProyectoTIC1.Imagenes.Imagen;
import com.example.PrimerProyectoTIC1.Reserva.Reserva;
import com.example.PrimerProyectoTIC1.User.Empleado;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.dynalink.linker.LinkerServices;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Actividad {

    @OneToMany
    @JoinColumn(name = "ActividadId")
    List<Reserva> Reservas;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "centro_deportivo_1_id", nullable = false)
    @JsonBackReference
    private CentroDeportivo1 centroDeportivo1;

    @Column
    private String horario;

    @Column
    private String dia;

    @Column
    private Integer cupos;
    @Column
    private String nombre;
    @Column
    private Float precio;

    //@Column
    //@Lob
    //private String imagen;
    @Column
    private Boolean reserva;
    @Column
    private String tipoActividad;
    @Column String descripcion;

    public List<Reserva> getReservas() {
        return Reservas;
    }


    public void setReservas(List<Reserva> reservas) {
        Reservas = reservas;
    }

    public CentroDeportivo1 getCentroDeportivo1() {
        return centroDeportivo1;
    }

    public Long getCentro_deportivo_1_id(){
        return centroDeportivo1.getId();
    }
    public void setCentro_deportivo_1_id(Long id){
        centroDeportivo1.setId(id);
    }


    public Actividad(CentroDeportivo1 centroDeportivo1, String horario, Integer cupos, String nombre, Float precio, Boolean reserva, String tipoActividad,String descripcion, String dia) {
        this.centroDeportivo1 = centroDeportivo1;
        this.horario = horario;
        this.cupos = cupos;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.dia = dia;
        this.reserva = reserva;
        this.tipoActividad = tipoActividad;
    }

    public void setCentroDeportivo1(CentroDeportivo1 centroDeportivo1) {
        this.centroDeportivo1 = centroDeportivo1;
    }




    public Actividad() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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





    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

