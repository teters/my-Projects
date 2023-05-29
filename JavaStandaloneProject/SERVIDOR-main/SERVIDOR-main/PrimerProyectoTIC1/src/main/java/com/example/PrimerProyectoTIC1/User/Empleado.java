package com.example.PrimerProyectoTIC1.User;

import com.example.PrimerProyectoTIC1.Empresas.Empresa1;
import com.example.PrimerProyectoTIC1.Reserva.Reserva;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "empleados")

public class Empleado extends User1{

    @OneToMany
    @JoinColumn(name = "empleadoId")
    private List<Reserva> Reservas;


    @Column
    private Float saldo;





    //@ManyToOne(cascade = CascadeType.ALL) //para que se persistan las instancias referenciadas
    //@JoinColumn(name = "empresaID")
    //private Empresa1 empresa;

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }


    public Empleado(String nombre, Long telefono, String mail, String fechaVenc, String password, Float saldo, Long empresaID) {
        super( nombre, telefono, mail, password);
        this.fechaVenc = fechaVenc;
        this.saldo=saldo;
        this.empresaID=empresaID;
    }

    public Long getEmpresaID() {
        return empresaID;
    }

    public void setEmpresaID(Long empresaID) {
        this.empresaID = empresaID;
    }

    public Empleado() {
    }
    @Column
    private String fechaVenc;
    @Column
    private Long empresaID;

    public String getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(String fechaVenc) {
        this.fechaVenc = fechaVenc;
    }


}
