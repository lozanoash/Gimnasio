package com.lozanoash.gimnasio.SERVICE;

import com.lozanoash.gimnasio.MODEL.Socio;
import com.lozanoash.gimnasio.REPOSITORY.SocioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SocioService {
    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository){
        this.socioRepository = socioRepository;

    }

    //metodo auxiliar: lanza un 404 si el socio no fue encontrado
    private Socio buscar(int id){
        return socioRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Socio no encontrado"
                ));
    }
    //trer todos
    public List<Socio> traerTodos(){
        return socioRepository.findAll();
    }
    public Socio traerUno(int id){
        return buscar(id);
    }
    public Socio guardar(Socio socio){
        socio.setId(0); //evita que editen el post
        return socioRepository.save(socio);
    }
    public Socio actualizar(int id, Socio datos){
        buscar(id);
        datos.setId(id);
        return socioRepository.save(datos);
    }
    public void eliminar(int id){
        buscar(id);
        socioRepository.deleteById(id);
    }
    //situacion especial:
    public double ingresos(){
        return socioRepository.findByActivoTrue().stream()
                .mapToDouble(Socio::getMensualidad)
                .sum();
    }
    public Socio desactivar(int id){
        Socio socio = buscar(id);
        if (!socio.isActivo()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El socio ya estaba inactivo"
            );

        }
        socio.setActivo(false);
        return socioRepository.save(socio);
    }

}
