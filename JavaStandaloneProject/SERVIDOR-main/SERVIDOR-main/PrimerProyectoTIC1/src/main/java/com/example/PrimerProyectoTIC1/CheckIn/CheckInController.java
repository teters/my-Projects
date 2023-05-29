package com.example.PrimerProyectoTIC1.CheckIn;


import com.example.PrimerProyectoTIC1.Actividades.Actividad;
import com.example.PrimerProyectoTIC1.Actividades.ActividadService;
import com.example.PrimerProyectoTIC1.User.Empleado;
import com.example.PrimerProyectoTIC1.User.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    CheckInService checkInService;

    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    ActividadService actividadService;
    @PostMapping("/ase/")
    public String hola(){
        System.out.println("hola");
        return "hola";
    }

    @PostMapping("/")
    public String hacerCheckIn(@RequestBody CheckinDTO checkIn){//validar si existe, descontar saldo
        String empleado_id=checkIn.getMail_empleado();
        Empleado empleado=empleadoService.obtenerEmpleadoPorMailsolamente(empleado_id);

        Actividad actividad=actividadService.obtenerActividadPorId(checkIn.getId_actividad());
        LocalDateTime hora=LocalDateTime.now();
        CheckIn check=new CheckIn();
        check.setActividad(actividad);
        check.setEmpleado(empleado);
        check.setHora(hora);
        Boolean estaVencido=checkInService.validarCarneDeSalud(check.getEmpleado());
        String mail = check.getEmpleado().getMail();
        Float precio = check.getActividad().getPrecio();
        String contra = check.getEmpleado().getPassword();
        String estado="no";
        if(! estaVencido) {
            empleadoService.descontarSaldo(mail, contra, precio);
            checkInService.agregarCheckin(check);
            estado="si";
        }
        return estado;

    }

}
