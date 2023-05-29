package com.example.PrimerProyectoTIC1.User;

import com.example.PrimerProyectoTIC1.CentrosDeportivos.CentroDeportivo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BossEmpresaService {
    @Autowired
    BossEmpresaRepository bossEmpresaRepository;
    public void agregaBossEmpresa(BossEmpresa1 boss){
        bossEmpresaRepository.save(boss);
    }
}
