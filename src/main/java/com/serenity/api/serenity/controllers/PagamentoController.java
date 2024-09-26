package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.pagamento.PagamentoRequest;
import com.serenity.api.serenity.dtos.pagamento.PagamentoResponse;
import com.serenity.api.serenity.dtos.pagamento.PagamentoUpdateRequest;
import com.serenity.api.serenity.services.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;


    @GetMapping
    public ResponseEntity<List<PagamentoResponse>> buscar() {
        List<PagamentoResponse> pagamentoResponses = pagamentoService.listar();
        return pagamentoResponses.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(pagamentoResponses);
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> cadastrar(@RequestBody PagamentoRequest pagamentoRequest) {
        return ResponseEntity.status(201).body(pagamentoService.cadastrar(pagamentoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(pagamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponse> atualizar(@PathVariable Integer id, @RequestBody PagamentoUpdateRequest pagamentoUpdateRequest) {
        return ResponseEntity.status(200).body(pagamentoService.atualizar(id, pagamentoUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Integer id) {
        pagamentoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}