package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.parceiro.ParceiroRequest;
import com.serenity.api.serenity.dtos.parceiro.ParceiroResponse;
import com.serenity.api.serenity.models.Parceiro;
import com.serenity.api.serenity.services.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parceiros")
public class ParceiroController {
    @Autowired
    private ParceiroService parceiroService;

    @GetMapping
    public ResponseEntity<List<ParceiroResponse>> buscar() {
        List<ParceiroResponse> parceiroResponses = parceiroService.listar();
        return parceiroResponses.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(parceiroResponses);
    }

    @PostMapping
    public ResponseEntity<Parceiro> cadastrar(@RequestBody ParceiroRequest parceiroRequest) {
        return ResponseEntity.status(201).body(parceiroService.cadastrar(parceiroRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParceiroResponse> buscarPorId(@PathVariable int id){
        return ResponseEntity.status(200).body(parceiroService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParceiroResponse> atualizar(@PathVariable int id, @RequestBody Parceiro parceiroAtualizado) {
        return  ResponseEntity.status(200).body(parceiroService.atualizar(id, parceiroAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable int id) {
        parceiroService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
