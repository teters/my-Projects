package com.example.PrimerProyectoTIC1.CheckIn;

import com.example.PrimerProyectoTIC1.Actividades.Actividad;
import com.example.PrimerProyectoTIC1.User.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CheckInService {


    @Autowired
    CheckInRepository CheckInRepository;

    public void agregarCheckin(CheckIn checkIn){
        CheckInRepository.save(checkIn);
    }
    public Boolean validarCarneDeSalud(Empleado empleado){
        Boolean vencido=true;
        String vencString=empleado.getFechaVenc();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate venc=LocalDate.parse(vencString,formatter);
        if(venc.isAfter(LocalDate.now())){
            vencido=false;
        }
        return vencido;
    }
    public Boolean validarPorCupos(Actividad actividad){
        Boolean hayCupos=true;
        if((actividad.getCupos()-1)==0){
            hayCupos=false;
        }
        return hayCupos;
    }

}
