package com.lozanoash.gimnasio.CONTROLLER;

import com.lozanoash.gimnasio.MODEL.ClaseGrupal;
import com.lozanoash.gimnasio.SERVICE.ClaseGrupalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clase")
public class ClaseGrupalController {
    private final ClaseGrupalService claseService;

    public ClaseGrupalController(ClaseGrupalService claseService) {
        this.claseService = claseService;
    }

    @GetMapping
    public List<ClaseGrupal> verTodos() {
        return claseService.traerTodos();
    }

    @GetMapping("/{id}")
    public ClaseGrupal traerUno(@PathVariable int id) {
        return claseService.traerUno(id);
    }

    @PostMapping
    public ResponseEntity<ClaseGrupal> guardar(@RequestBody ClaseGrupal clase) {
        return new ResponseEntity<>(claseService.guardar(clase), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ClaseGrupal actualizar(@PathVariable int id, @RequestBody ClaseGrupal clase) {
        return claseService.actualizar(id, clase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        claseService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //  Especiales
    @PutMapping("/{id}/inscribir")
    public ClaseGrupal inscribir(@PathVariable int id) {
        return claseService.inscribir(id);
    }

    @PutMapping("/{id}/cancelar-inscripcion")
    public ClaseGrupal cancelar(@PathVariable int id) {
        return claseService.cancelarInscripcion(id);
    }

    @GetMapping("/con-cupos")
    public List<ClaseGrupal> conCupos() {
        return claseService.conCupos();
    }
}
