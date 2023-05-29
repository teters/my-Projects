package com.example.PrimerProyectoTIC1.Actividades;

import com.example.PrimerProyectoTIC1.Actividades.ActividadService;
import com.example.PrimerProyectoTIC1.CentrosDeportivos.CentroDeportivo1;
import com.example.PrimerProyectoTIC1.CentrosDeportivos.CentroDeportivoService;
import com.example.PrimerProyectoTIC1.Reserva.Reserva;
import com.example.PrimerProyectoTIC1.User.Empleado;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actividad")
public class ActividadesController {

    @Autowired
    ActividadService actividadService;
    @Autowired
    CentroDeportivoService centroDeportivoService;

    @PostMapping("/")
    public void guardarDatos(@RequestBody ActividadDTO actividad1){
        Actividad actividad=new Actividad();
        actividad.setCupos(actividad1.getCupos());
        actividad.setTipoActividad(actividad1.getTipoActividad());
        actividad.setDia(actividad1.getDia());
        actividad.setDescripcion(actividad1.getDescripcion());
        actividad.setHorario(actividad1.getHorario());
        actividad.setNombre(actividad1.getNombre());
        actividad.setPrecio(actividad1.getPrecio());
        actividad.setReserva(actividad1.getReservaBool());
        actividad.setReservas(new ArrayList<>());
        CentroDeportivo1 centroDeportivo1=centroDeportivoService.getCentroByNombre(actividad1.getNombreCentro());
        actividad.setCentroDeportivo1(centroDeportivo1);
        actividadService.agregarActividad(actividad);
    }
    @GetMapping("/")
    public List<Actividad> obtenerListaDeActividades(){
        return actividadService.obtenerListaDeActividades();
    }
    @GetMapping("/todas")
    public List<Actividad> obtenerNombres(){
        List<Actividad> toReturn=actividadService.obtenerListaDeActividadesPorNombre();
        return toReturn;
    }
    @GetMapping("/{nombre}/centros")
    @ResponseBody
    public List<String> obtenerNombreCentros(@PathVariable String nombre){
        return actividadService.obtenerCentrosDeUnaActividad(nombre);}


    @GetMapping("/{nombre}/horarios")
    @ResponseBody
    public List<String> obtenerHorarios(@PathVariable String nombre){
        return actividadService.obtenerHorariosDeUnaActividad( nombre);
    }
    @GetMapping("/{actividad}/{centro}/horarios")
    @ResponseBody
    public List<String> obtenerHorariosActividadCentro(@PathVariable String actividad,@PathVariable String centro){
        return actividadService.obtenerHorariosActividadCentro(actividad,centro);
    }
    @GetMapping("/{actividad}/{horario}/centro")
    @ResponseBody
    public List<String> obtenerCentrosAPartirDeHorarios(@PathVariable String actividad,@PathVariable String horario){
        return actividadService.obtenerCentrosAPartirDeHorario(horario,actividad);
    }
    @GetMapping("/{nombre}/")
    @ResponseBody
    public Actividad obtenerActividadConNombre(@PathVariable String nombre){
        return actividadService.obtenerActividadConNombre(nombre);
    }
    @GetMapping("/{nombre}/dias/")
    @ResponseBody
    public List<String> obtenerDias(@PathVariable String nombre){
        return actividadService.obtenerDias(nombre);
    }






}

