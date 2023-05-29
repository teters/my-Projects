package com.example.PrimerProyectoTIC1.CheckIn;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, String> {
    //List<Reserva> findByNombre(String nombre);
}
