package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoRequest;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoResponse;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoUpdateRequest;
import com.serenity.api.serenity.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> buscar() {
        List<AgendamentoResponse> agendamentoResponses = agendamentoService.listar();
        return agendamentoResponses.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponse> cadastrar(@RequestBody AgendamentoRequest agendamentoRequest) {
        return ResponseEntity.status(201).body(agendamentoService.cadastrar(agendamentoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(agendamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> atualizar(@PathVariable Integer id, @RequestBody AgendamentoUpdateRequest agendamentoUpdateRequest) {
        return ResponseEntity.status(200).body(agendamentoService.atualizar(id, agendamentoUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Integer id) {
        agendamentoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}