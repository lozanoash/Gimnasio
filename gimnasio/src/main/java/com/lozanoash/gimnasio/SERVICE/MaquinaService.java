package com.lozanoash.gimnasio.SERVICE;

import com.lozanoash.gimnasio.MODEL.Maquina;
import com.lozanoash.gimnasio.REPOSITORY.MaquinaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;

@Service
public class MaquinaService {
    private final MaquinaRepository maquinaRepository;

    public MaquinaService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    private Maquina buscar(int id) {
        return maquinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Maquina no encontrada"));
    }


    public List<Maquina> traerTodos() {
        return maquinaRepository.findAll();
    }

    public Maquina traerUno(int id) {
        return buscar(id);
    }

    public Maquina guardar(Maquina maquina) {
        maquina.setId(0);
        return maquinaRepository.save(maquina);
    }

    public Maquina actualizar(int id, Maquina datos) {
        buscar(id);
        datos.setId(id);
        return maquinaRepository.save(datos);
    }

    public void eliminar(int id) {
        buscar(id);
        maquinaRepository.deleteById(id);
    }

    // Especiales
    public List<Maquina> operativas() {
        return maquinaRepository.findByEnMantenimientoFalse();
    }

    public List<Maquina> antiguas(int anios) {
        int limite = Year.now().getValue() - anios;
        return maquinaRepository.findByAnioCompraLessThan(limite);
    }

    public Maquina enviarAMantenimiento(int id) {
        Maquina maquina = buscar(id);
        if (maquina.isEnMantenimiento()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya estaba en mantenimiento");
        }
        maquina.setEnMantenimiento(true);
        return maquinaRepository.save(maquina);
    }
}
