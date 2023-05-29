package com.example.PrimerProyectoTIC1.Imagenes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    ImagenService imagenService;

    @PostMapping("/")
    public void guardarImagenes (@RequestBody Imagen imagen){
        String nombreact=imagen.getNombreActividad();
        imagenService.agregarImagen(imagen);
    }
    @GetMapping("/{nombreActividad}/")
    @ResponseBody
    public Imagen getImagenes(@PathVariable String nombreActividad){
        return imagenService.buscarImagenes(nombreActividad);
    }

}
