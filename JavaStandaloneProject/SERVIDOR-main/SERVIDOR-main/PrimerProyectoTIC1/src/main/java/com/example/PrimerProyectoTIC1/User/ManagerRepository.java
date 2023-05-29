package com.example.PrimerProyectoTIC1.User;

import com.example.PrimerProyectoTIC1.Empresas.Empresa1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<BossEmpresa1, Long>{}


