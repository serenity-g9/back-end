package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.escala.EscalaRequest;
import com.serenity.api.serenity.dtos.escala.EscalaResponse;
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
    public ResponseEntity<List<EscalaResponse>> buscar() {
        List<EscalaResponse> escalaResponses = escalaService.listar();
        return escalaResponses.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(escalaResponses);
    }

    @PostMapping
    public ResponseEntity<Escala> cadastrar(@RequestBody EscalaRequest escalaRequest) {
        return ResponseEntity.status(201).body(escalaService.cadastrar(escalaRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscalaResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(escalaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscalaResponse> atualizar(@PathVariable int id, @RequestBody Escala escala) {
        return ResponseEntity.status(200).body(escalaService.atualizar(id, escala));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable int id) {
        escalaService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}