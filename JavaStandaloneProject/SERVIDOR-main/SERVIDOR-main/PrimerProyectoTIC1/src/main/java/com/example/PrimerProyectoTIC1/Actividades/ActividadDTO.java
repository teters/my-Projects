package com.example.PrimerProyectoTIC1.Actividades;

public class ActividadDTO {
    private Integer cupos;
    private String descripcion;
    private String dia;
    private String horario;
    private String nombre;
    private Float precio;
    private String tipoActividad;
    private Boolean reservaBool;
    private String nombreCentro;

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cuposString) {
        this.cupos = cuposString;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipo) {
        this.tipoActividad = tipo;
    }

    public Boolean getReservaBool() {
        return reservaBool;
    }

    public void setReservaBool(Boolean reservaBool) {
        this.reservaBool = reservaBool;
    }
}
