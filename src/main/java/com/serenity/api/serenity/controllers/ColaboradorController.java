package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @GetMapping
    public ResponseEntity<List<Colaborador>> buscar() {
        List<Colaborador> colaboradores = colaboradorRepository.findAll();

        if (colaboradores.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(colaboradores);
    }

    @PostMapping
    public ResponseEntity<Colaborador> cadastrar(@RequestBody Colaborador colaborador) {
        colaborador.setId(null);
        return ResponseEntity.status(201).body(colaboradorRepository.save(colaborador));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> buscarPorId(@PathVariable int id){
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);

        if (colaborador.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(colaborador.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> atualizar(@PathVariable int id, @RequestBody Colaborador colaboradorAtualizado) {
        if (!colaboradorRepository.existsById(id)){
            return ResponseEntity.status(404).build();
        }

        colaboradorAtualizado.setId(id);
        Colaborador colaboradorRetorno = colaboradorRepository.save(colaboradorAtualizado);
        return  ResponseEntity.status(200).body(colaboradorRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Colaborador> deletar (@PathVariable int id) {
        if (colaboradorRepository.existsById(id)){
            colaboradorRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
