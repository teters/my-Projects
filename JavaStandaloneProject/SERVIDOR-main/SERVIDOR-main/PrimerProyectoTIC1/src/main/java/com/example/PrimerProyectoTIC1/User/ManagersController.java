package com.example.PrimerProyectoTIC1.User;

import com.example.PrimerProyectoTIC1.Empresas.Empresa1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/manager")
public class ManagersController {
    @Autowired
    ManagerService managerService;
    @PostMapping("/")
    public void guardarManager(@RequestBody BossEmpresa1 user) {
        String nombre = user.getNombre();
        Long telefono = user.getTelefono();
        String mail=user.getMail();
        String password=user.getPassword();
        managerService.agregarManager(nombre,telefono,mail,password);
    }
    @GetMapping("/")
    public List<BossEmpresa1> listaDeManagers(){
        return managerService.obtenerListaDeManagers();
    }


}
