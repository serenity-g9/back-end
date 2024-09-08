package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.services.EscalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escalas")
public class EscalaController {
    @Autowired
    private EscalaService escalaService;

    @GetMapping
    public ResponseEntity<List<Escala>> buscar() {
        List<Escala> escalas = escalaService.listar();
        return escalas.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(escalas);
    }

    @PostMapping
    public ResponseEntity<Escala> cadastrar(@RequestBody Escala escala) {
        return ResponseEntity.status(201).body(escalaService.cadastrar(escala));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escala> buscarPorId(@PathVariable int id) {
        return ResponseEntity.status(200).body(escalaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escala> atualizar(@PathVariable int id, @RequestBody Escala escala) {
        return ResponseEntity.status(200).body(escalaService.atualizar(id, escala));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Escala> deletar (@PathVariable int id) {
        escalaService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}