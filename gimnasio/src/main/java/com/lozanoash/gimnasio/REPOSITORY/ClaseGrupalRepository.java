package com.lozanoash.gimnasio.REPOSITORY;

import com.lozanoash.gimnasio.MODEL.ClaseGrupal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseGrupalRepository extends JpaRepository<ClaseGrupal, Integer> {
    List<ClaseGrupal> findByCuposGreaterThanOrderByCuposDesc(int cupos);
}
