package com.example.PrimerProyectoTIC1.CentrosDeportivos;


import com.example.PrimerProyectoTIC1.Actividades.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroDeportivoService {

    @Autowired
    CentroDeportivoRepository centroDeportivoRepository;
    public void agregarCentroDeportivo(String nombre,String direccion){
        CentroDeportivo1 cd1 =new CentroDeportivo1();
        cd1.setNombre(nombre);
        cd1.setDireccion(direccion);
        centroDeportivoRepository.save(cd1);
    }

    public List<CentroDeportivo1> obtenerListaDeCentrosDeportivos(){
        return centroDeportivoRepository.findAll();
    }
    public List<Actividad> getActivities(Long centroid){
        CentroDeportivo1 centro=centroDeportivoRepository.findById(centroid).get();
        return centro.getActividads();
    }
    public String getCentroById(Long id){
        return centroDeportivoRepository.findById(id).get().getNombre();
    }
    public CentroDeportivo1 getCentroByNombre(String nombre){
        return centroDeportivoRepository.findByNombre(nombre).get();
    }
}