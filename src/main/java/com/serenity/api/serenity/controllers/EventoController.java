package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public ResponseEntity<List<Evento>> buscar() {
        List<Evento> eventos = eventoRepository.findAll();

        if (eventos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(eventos);
    }

    @PostMapping
    public ResponseEntity<Evento> cadastrar(@RequestBody Evento evento) {
        evento.setId(null);
        return ResponseEntity.status(201).body(eventoRepository.save(evento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable int id) {
        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(evento.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable int id, @RequestBody Evento eventoAtualizado) {
        if (!eventoRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        eventoAtualizado.setId(id);
        Evento eventoRetorno = eventoRepository.save(eventoAtualizado);
        return  ResponseEntity.status(200).body(eventoRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Evento> deletar (@PathVariable int id) {
        if (eventoRepository.existsById(id)){
            eventoRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }
}
