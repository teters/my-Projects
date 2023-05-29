package com.example.PrimerProyectoTIC1.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

    int countByNombre(String nombre);
    @Query("select e from Empleado e where e.mail=?1")
    Empleado findByMail(String mail);

    Optional<Empleado> findById(Long id);
}
