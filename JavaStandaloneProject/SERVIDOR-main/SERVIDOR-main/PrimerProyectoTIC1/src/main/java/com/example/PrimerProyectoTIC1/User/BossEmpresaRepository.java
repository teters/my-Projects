package com.example.PrimerProyectoTIC1.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BossEmpresaRepository extends JpaRepository<BossEmpresa1,Long> {
    @Query("select b from BossEmpresa1 b where b.mail=?1")
    BossCD1 findByMail(String mail);
}
