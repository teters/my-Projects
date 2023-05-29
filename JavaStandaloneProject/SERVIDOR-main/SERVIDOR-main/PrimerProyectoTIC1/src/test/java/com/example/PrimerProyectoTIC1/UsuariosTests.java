package com.example.PrimerProyectoTIC1;


import com.example.PrimerProyectoTIC1.User.BossCD1;
import com.example.PrimerProyectoTIC1.User.BossEmpresa1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class UsuariosTests {
    @Test
    public void herenciaDeUsuario(){
        BossEmpresa1 bossEmpresa= new BossEmpresa1();
        bossEmpresa.setNombre("teo");
        assertEquals("teo",bossEmpresa.getNombre());

        BossCD1 bossCD = new BossCD1();
        bossEmpresa.setNombre("aaaa");
        assertEquals("aaaa", bossCD.getNombre());

    }
}
