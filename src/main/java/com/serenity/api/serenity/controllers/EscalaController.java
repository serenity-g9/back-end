package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import com.serenity.api.serenity.repositories.EscalaRepository;
import com.serenity.api.serenity.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/escalas")
public class EscalaController {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private EscalaRepository escalaRepository;

    @GetMapping
    public ResponseEntity<List<Escala>> buscar() {
        List<Escala> escalas = escalaRepository.findAll();

        if (escalas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(escalas);
    }

    @PostMapping
    public ResponseEntity<Escala> cadastrar(@RequestBody Escala escala) {
        escala.setId(null);

        if (!colaboradorRepository.existsById(escala.getIdColaborador()) || !eventoRepository.existsById(escala.getIdEvento())) {
            return ResponseEntity.status(404).build();
        }

        escala.setDataHorario(LocalDateTime.now());

        return ResponseEntity.status(201).body(escalaRepository.save(escala));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escala> buscarPorId(@PathVariable int id) {
        Optional<Escala> escala = escalaRepository.findById(id);

        if (escala.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(escala.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escala> atualizar(@PathVariable int id, @RequestBody Escala escalaAtualizado) {
        if (!escalaRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        escalaAtualizado.setId(id);
        Escala escalaRetorno = escalaRepository.save(escalaAtualizado);
        return  ResponseEntity.status(200).body(escalaRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Escala> deletar (@PathVariable int id) {
        if (escalaRepository.existsById(id)){
            escalaRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }


}
