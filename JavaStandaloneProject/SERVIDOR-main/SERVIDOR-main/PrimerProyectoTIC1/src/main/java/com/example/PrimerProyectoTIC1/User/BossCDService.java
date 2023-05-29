package com.example.PrimerProyectoTIC1.User;

import com.example.PrimerProyectoTIC1.CentrosDeportivos.CentroDeportivo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

@Service
public class BossCDService {
    @Autowired
    BossCDRepository bossCDRepository;
    public BossCD1 obtenerBossConMail(String mail, String password){
        BossCD1 boss=null;

        boss=bossCDRepository.findByMail(mail);


        try {
            if (boss.getPassword().equals(password)) {
                return boss;
            }
        }catch(Exception e){
            boss=new BossCD1("",2L,"co","dk",new CentroDeportivo1());
        }
        return boss;

    }
    public void agregarManager(BossCD1 boss){
        bossCDRepository.save(boss);
    }
}
