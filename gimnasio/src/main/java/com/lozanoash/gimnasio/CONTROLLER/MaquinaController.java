package com.lozanoash.gimnasio.CONTROLLER;

import com.lozanoash.gimnasio.MODEL.Maquina;
import com.lozanoash.gimnasio.SERVICE.MaquinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquina")
public class MaquinaController {
    private final MaquinaService maquinaService;

    public MaquinaController(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @GetMapping
    public List<Maquina> verTodos() {
        return maquinaService.traerTodos();
    }

    @GetMapping("/{id}")
    public Maquina traerUno(@PathVariable int id) {
        return maquinaService.traerUno(id);
    }

    @PostMapping
    public ResponseEntity<Maquina> guardar(@RequestBody Maquina maquina) {
        return new ResponseEntity<>(maquinaService.guardar(maquina), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Maquina actualizar(@PathVariable int id, @RequestBody Maquina maquina) {
        return maquinaService.actualizar(id, maquina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        maquinaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ===== Especiales =====
    @GetMapping("/operativas")
    public List<Maquina> operativas() {
        return maquinaService.operativas();
    }

    @GetMapping("/antiguas")
    public List<Maquina> antiguas(@RequestParam int anios) {
        return maquinaService.antiguas(anios);
    }

    @PutMapping("/{id}/mantenimiento")
    public Maquina mantenimiento(@PathVariable int id) {
        return maquinaService.enviarAMantenimiento(id);
    }
}
