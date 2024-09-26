package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.registro.RegistroRequest;
import com.serenity.api.serenity.dtos.registro.RegistroResponse;
import com.serenity.api.serenity.services.RegistroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
@RequiredArgsConstructor
public class RegistroController {
    private final RegistroService registroService;

    @GetMapping
    public ResponseEntity<List<RegistroResponse>> buscar() {
        List<RegistroResponse> registrosResponses = registroService.listar();
        return registrosResponses.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(registrosResponses);
    }

    @PostMapping
    public ResponseEntity<RegistroResponse> cadastrar(@RequestBody RegistroRequest registroRequest) {
        return ResponseEntity.status(201).body(registroService.cadastrar(registroRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponse> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(registroService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Integer id) {
        registroService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
