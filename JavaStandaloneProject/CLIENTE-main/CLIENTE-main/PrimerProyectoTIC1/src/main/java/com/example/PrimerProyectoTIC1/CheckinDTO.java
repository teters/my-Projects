package com.example.PrimerProyectoTIC1;


public class CheckinDTO {
        private String mail_empleado;
        private Long id_actividad;
        private String hora;



        public String getMail_empleado() {
            return mail_empleado;
        }

        public void setMail_empleado(String id_empleado) {
            this.mail_empleado = id_empleado;
        }

        public Long getId_actividad() {
            return id_actividad;
        }

        public void setId_actividad(Long id_actividad) {
            this.id_actividad = id_actividad;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }
    }

