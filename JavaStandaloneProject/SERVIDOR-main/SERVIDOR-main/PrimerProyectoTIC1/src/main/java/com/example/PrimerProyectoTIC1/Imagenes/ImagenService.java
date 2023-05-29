package com.example.PrimerProyectoTIC1.Imagenes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    public void agregarImagen(Imagen imagen){
        imagenRepository.save(imagen);
    }
    public Imagen buscarImagenes(String nombreactividad){
        return imagenRepository.findByNombreActividad(nombreactividad).get();
    }

}