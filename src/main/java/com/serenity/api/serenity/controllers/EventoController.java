package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.evento.EventoRequest;
import com.serenity.api.serenity.dtos.evento.EventoResponse;
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
    public ResponseEntity<List<EventoResponse>> buscar() {
        List<EventoResponse> eventosResponse = eventoService.listar();
        return eventosResponse.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(eventosResponse);
    }

    @PostMapping
    public ResponseEntity<EventoResponse> cadastrar(@RequestBody EventoRequest eventoRequest) {
        return ResponseEntity.status(201).body(eventoService.cadastrar(eventoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(eventoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponse> atualizar(@PathVariable Integer id, @RequestBody EventoRequest eventoRequest) {
        return  ResponseEntity.status(200).body(eventoService.atualizar(id, eventoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Integer id) {
        eventoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
