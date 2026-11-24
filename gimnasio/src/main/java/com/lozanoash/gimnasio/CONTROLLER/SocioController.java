package com.lozanoash.gimnasio.CONTROLLER;

import com.lozanoash.gimnasio.MODEL.Socio;
import com.lozanoash.gimnasio.SERVICE.SocioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socio")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService){
        this.socioService =socioService;

    }

    @GetMapping
    public List<Socio> verTodos(){
        return socioService.traerTodos();
    }
    //traer uno
    @GetMapping("/{id}")
    public Socio traerUno(@PathVariable int id){
        return socioService.traerUno(id);
    }
//guardar
    @PostMapping
    public ResponseEntity<Socio> guardar(@RequestBody Socio socio) {
        return new ResponseEntity<>(socioService.guardar(socio), HttpStatus.CREATED);
    }
    //editar
    @PutMapping("/{id}")
    public Socio actualizar(@PathVariable int id, @RequestBody Socio socio) {
        return socioService.actualizar(id, socio);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        socioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //especiales

    @GetMapping("/ingresos")
    public double ingresos(){
        return socioService.ingresos();
    }

    @PutMapping("/{id}/desactivar")
    public Socio desactivar(@PathVariable int id){
        return socioService.desactivar(id);
    }

}
