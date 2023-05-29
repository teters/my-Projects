package com.example.PrimerProyectoTIC1.User;

import com.example.PrimerProyectoTIC1.CentrosDeportivos.CentroDeportivo1;
import com.example.PrimerProyectoTIC1.CentrosDeportivos.CentroDeportivoService;
import com.example.PrimerProyectoTIC1.Empresas.Empresa1;
import com.example.PrimerProyectoTIC1.Empresas.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("managerempresa")
public class BossEmpresaController {
    private Empresa1 empresa;
    @Autowired
    BossEmpresaService bossEmpresaService;

    @Autowired
    EmpresaService empresaService;

    @PostMapping("/")
    public void guardarBossEmpresa(@RequestBody BossEmpresaDTO boss1){
        BossEmpresa1 boss = new BossEmpresa1();
        //Empresa1 empresa1 = empresaService.ObtenerConNombre(boss1.getEmpresaNombre());
        boss.setMail(boss1.getMail());
        boss.setNombre(boss1.getNombre());
        boss.setTelefono(boss1.getTelefono());
        boss.setPassword(boss1.getPassword());
        //boss.setEmpresa(empresa1);
        bossEmpresaService.agregaBossEmpresa(boss);
    }




}
