package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> buscar() {
        List<Agendamento> agendamentos = agendamentoService.listar();
        return agendamentos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(agendamentos);
    }

    @PostMapping
    public ResponseEntity<Agendamento> cadastrar(@RequestBody AgendamentoRequest agendamentoRequest) {
        return ResponseEntity.status(201).body(agendamentoService.cadastrar(agendamentoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(agendamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable int id, @RequestBody Agendamento agendamento) {
        return ResponseEntity.status(200).body(agendamentoService.atualizar(id, agendamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Agendamento> deletar (@PathVariable int id) {
        agendamentoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}