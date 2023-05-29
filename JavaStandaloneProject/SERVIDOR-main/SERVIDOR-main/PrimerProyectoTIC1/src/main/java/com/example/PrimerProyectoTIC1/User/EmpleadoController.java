package com.example.PrimerProyectoTIC1.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    private Empleado empleadoLogin;
    @Autowired
    EmpleadoService empleadoService;
    @PostMapping ("/iniciosesion")
    public Empleado login(@RequestBody UsuarioLogin empleado){
        String mail= empleado.getMail();
        String password=empleado.getPassword();
        Empleado empleado1=empleadoService.obtenerEmpleadoPorMail(mail,password);
        empleadoLogin=empleado1;
        return empleado1;

    }
    @PostMapping("/")
    public void agregarEmpleado(@RequestBody Empleado empleado){
        String nombre=empleado.getNombre();
        String mail= empleado.getMail();
        String password=empleado.getPassword();
        Long tel= empleado.getTelefono();
        Float saldo=empleado.getSaldo();
        String fechaVenc=empleado.getFechaVenc();
        Long empresaId=empleado.getEmpresaID();
        empleadoService.crearEmpleado(nombre,tel,mail,fechaVenc,password,saldo,empresaId);
    }
    @GetMapping("/")
    public List<Empleado> obtenerListaDeEmpleados(){
        return empleadoService.getListaDeEmpleados();
    }

    public Empleado getEmpleadoLogin() {
        return empleadoLogin;
    }
    @GetMapping("/getEmpleadoInicio")
    public Empleado obtenerEmpleadoInicio(){
        return empleadoLogin;
    }
    @GetMapping("/{mail}/")
    @ResponseBody
    public Empleado obtenerEmpleadoConMail(@PathVariable String mail){
        return empleadoService.obtenerEmpleadoPorMailsolamente(mail);
    }

    public void setEmpleadoLogin(Empleado empleadoLogin) {
        this.empleadoLogin = empleadoLogin;
    }
}
