package com.example.PrimerProyectoTIC1.CentrosDeportivos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentroDeportivoRepository extends JpaRepository<CentroDeportivo1, Long>{
    @Query("select c from CentroDeportivo1 c where c.nombre = ?1")
    Optional<CentroDeportivo1> findByNombre(String nombre);
    @Query("select c from CentroDeportivo1 c where c.centro_id=?1")
    Optional<CentroDeportivo1> findById(Long id);







}
