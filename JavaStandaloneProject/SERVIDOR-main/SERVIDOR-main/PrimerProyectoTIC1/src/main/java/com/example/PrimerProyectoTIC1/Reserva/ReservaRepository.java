package com.example.PrimerProyectoTIC1.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("select r from Reserva r where r.empleadoId=?1")
    public List<Reserva> findAllByEmpleadoId(Long empleadoID);
}
