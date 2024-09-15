package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> buscar() {
        List<Evento> eventos = eventoService.listar();
        return eventos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(eventos);
    }

    @PostMapping
    public ResponseEntity<Evento> cadastrar(@RequestBody Evento evento) {
        return ResponseEntity.status(201).body(eventoService.cadastrar(evento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(eventoService.buscarPorId(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable int id, @RequestBody Evento eventoAtualizado) {
        return  ResponseEntity.status(200).body(eventoService.atualizar(id, eventoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Evento> deletar (@PathVariable int id) {
        eventoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
