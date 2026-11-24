package com.lozanoash.gimnasio.SERVICE;

import com.lozanoash.gimnasio.MODEL.ClaseGrupal;
import com.lozanoash.gimnasio.REPOSITORY.ClaseGrupalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClaseGrupalService {
    private final ClaseGrupalRepository claseRepository;

    public ClaseGrupalService(ClaseGrupalRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    private ClaseGrupal buscar(int id) {
        return claseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Clase no encontrada"));
    }


    public List<ClaseGrupal> traerTodos() {
        return claseRepository.findAll();
    }

    public ClaseGrupal traerUno(int id) {
        return buscar(id);
    }

    public ClaseGrupal guardar(ClaseGrupal clase) {
        clase.setId(0);
        return claseRepository.save(clase);
    }

    public ClaseGrupal actualizar(int id, ClaseGrupal datos) {
        buscar(id);
        datos.setId(id);
        return claseRepository.save(datos);
    }

    public void eliminar(int id) {
        buscar(id);
        claseRepository.deleteById(id);
    }

    // Especiales
    public ClaseGrupal inscribir(int id) {
        ClaseGrupal clase = buscar(id);
        if (clase.getCupos() <= 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No quedan cupos");
        }
        clase.setCupos(clase.getCupos() - 1);
        return claseRepository.save(clase);
    }

    public ClaseGrupal cancelarInscripcion(int id) {
        ClaseGrupal clase = buscar(id);
        clase.setCupos(clase.getCupos() + 1);
        return claseRepository.save(clase);
    }

    public List<ClaseGrupal> conCupos() {
        return claseRepository.findByCuposGreaterThanOrderByCuposDesc(0);
    }
}
