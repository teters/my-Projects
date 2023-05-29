package com.example.PrimerProyectoTIC1.Reserva;

import com.example.PrimerProyectoTIC1.Actividades.ActividadService;
import com.example.PrimerProyectoTIC1.User.Empleado;
import com.example.PrimerProyectoTIC1.User.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaService reservaService;
    @Autowired
    ActividadService actividadService;
    @Autowired
    EmpleadoService empleadoService;
    @PostMapping("/")
    public String guardarReserva(@RequestBody ReservaDTO reservaDTO){
        Reserva reserva=new Reserva();
        reserva.setActividadId(reservaDTO.getActividadId());
        reserva.setCentroId(reservaDTO.getCentroId());
        reserva.setDia(actividadService.obtenerActividadPorId(reservaDTO.getActividadId()).getDia());
        reserva.setFecha(reservaDTO.getFecha());
        reserva.setHora(actividadService.obtenerActividadPorId(reservaDTO.getActividadId()).getHorario());
        Empleado empl=empleadoService.obtenerEmpleadoPorMailsolamente(reservaDTO.getMail_empleado());
        Long empl_id=empl.getId();
        reserva.setEmpleadoId(empl_id);

        if(reservaService.validarPorCupos(reserva)){
            reservaService.agregarReserva(reserva);
            return "true";
        }
        return "false";
    }
    @GetMapping("/{mail}/")
    @ResponseBody
    public List<ReservaDTOConDia> obtenerReservasConMail(@PathVariable String mail){
        List<Reserva> r= reservaService.getReservasMail(mail);
        List<ReservaDTOConDia> reservaDTOS=new ArrayList<>();
        ReservaDTOConDia reservaDTO=new ReservaDTOConDia();
        Empleado empleadoTemp=null;
        for (int i = 0; i < r.size(); i++) {
            empleadoTemp=empleadoService.obtenerEmpleadoConId(r.get(i).getEmpleadoId());
            reservaDTO.setActividadId(r.get(i).getActividadId());
            reservaDTO.setCentroId(r.get(i).getCentroId());
            reservaDTO.setFecha(r.get(i).getFecha());
            reservaDTO.setMail_empleado(empleadoTemp.getMail());
            reservaDTO.setDia(r.get(i).getDia());
            reservaDTOS.add(reservaDTO);

        }
        return reservaDTOS;
    }
}
