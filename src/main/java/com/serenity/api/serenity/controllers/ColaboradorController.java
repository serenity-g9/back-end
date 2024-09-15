package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.colaborador.ColaboradorRequest;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorResponse;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorUpdateRequest;
import com.serenity.api.serenity.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public ResponseEntity<List<ColaboradorResponse>> buscar() {
        List<ColaboradorResponse> colaboradores = colaboradorService.listar();
        return colaboradores.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(colaboradores);
    }

    @PostMapping
    public ResponseEntity<ColaboradorResponse> cadastrar(@RequestBody ColaboradorRequest colaboradorRequest) {
        return ResponseEntity.status(201).body(colaboradorService.cadastrar(colaboradorRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(colaboradorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> atualizar(@PathVariable Integer id, @RequestBody ColaboradorUpdateRequest colaboradorUpdateRequest) {
        return  ResponseEntity.status(200).body(colaboradorService.atualizar(id, colaboradorUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Integer id) {
        colaboradorService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
