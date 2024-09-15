package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.registro.RegistroRequest;
import com.serenity.api.serenity.models.Registro;
import com.serenity.api.serenity.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroController {
    @Autowired
    private RegistroService registroService;

    @GetMapping
    public ResponseEntity<List<Registro>> buscar() {
        List<Registro> registros = registroService.listar();
        return registros.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(registros);
    }

    @PostMapping
    public ResponseEntity<Registro> cadastrar(@RequestBody RegistroRequest registroRequest) {
        return ResponseEntity.status(201).body(registroService.cadastrar(registroRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registro> buscarPorId(@PathVariable int id){
        return ResponseEntity.status(200).body(registroService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registro> atualizar(@PathVariable int id, @RequestBody Registro registroAtualizado) {
        return ResponseEntity.status(200).body(registroService.atualizar(id, registroAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Registro> deletar (@PathVariable int id) {
        registroService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
