package com.example.PrimerProyectoTIC1;

public class ReservaDTOConDia {
    private Long actividadId;
    private Long centroId;
    private String mail_empleado;
    private String fecha;
    private String dia;

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

    public String getMail_empleado() {
        return mail_empleado;
    }

    public void setMail_empleado(String mail_empleado) {
        this.mail_empleado = mail_empleado;
    }
}
