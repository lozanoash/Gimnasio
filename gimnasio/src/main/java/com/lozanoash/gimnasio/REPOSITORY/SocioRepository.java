package com.lozanoash.gimnasio.REPOSITORY;

import com.lozanoash.gimnasio.MODEL.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocioRepository extends JpaRepository<Socio, Integer> {
    List<Socio> findByActivoTrue();
}
