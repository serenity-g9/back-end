package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.comissao.ComissaoRequest;
import com.serenity.api.serenity.dtos.comissao.ComissaoResponse;
import com.serenity.api.serenity.dtos.comissao.ComissaoUpdateRequest;
import com.serenity.api.serenity.services.ComissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comissoes")
@RequiredArgsConstructor
public class ComissaoController {
    private final ComissaoService comissaoService;

    @GetMapping
    public ResponseEntity<List<ComissaoResponse>> buscar() {
        List<ComissaoResponse> comissaoResponses = comissaoService.listar();
        return comissaoResponses.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(comissaoResponses);
    }

    @PostMapping
    public ResponseEntity<ComissaoResponse> cadastrar(@RequestBody ComissaoRequest comissaoRequest) {
        return ResponseEntity.status(201).body(comissaoService.cadastrar(comissaoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComissaoResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(comissaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComissaoResponse> atualizar(@PathVariable Integer id, @RequestBody ComissaoUpdateRequest comissaoUpdateRequest) {
        return ResponseEntity.status(200).body(comissaoService.atualizar(id, comissaoUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Integer id) {
        comissaoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}