package com.example.PrimerProyectoTIC1.Imagenes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    @Transactional
    @Query("select i from Imagen i where i.nombreActividad=?1")
    Optional<Imagen> findByNombreActividad(String nombreActividad);
}
