package com.example.PrimerProyectoTIC1.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository repository;
    public void agregarManager(String nombre, Long tel, String mail,String password){
        repository.save(new BossEmpresa1(nombre,tel,mail,password));
    }
    public List<BossEmpresa1> obtenerListaDeManagers(){
        return repository.findAll();
    }
}
