package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.pagamento.PagamentoRequest;
import com.serenity.api.serenity.dtos.pagamento.PagamentoResponse;
import com.serenity.api.serenity.dtos.pagamento.PagamentoUpdateRequest;
import com.serenity.api.serenity.mappers.PagamentoMapper;
import com.serenity.api.serenity.services.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final PagamentoMapper mapper;

    @GetMapping
    public ResponseEntity<List<PagamentoResponse>> buscar() {
        List<PagamentoResponse> agendamentoResponses = pagamentoService.listar().stream()
                .map(PagamentoResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> cadastrar(@RequestBody @Valid PagamentoRequest pagamentoRequest) {
        return created(null).body(new PagamentoResponse(pagamentoService.cadastrar(mapper.toPagamento(pagamentoRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscarPorId(@PathVariable Integer id) {
        return ok(new PagamentoResponse(pagamentoService.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponse> atualizar(@PathVariable Integer id, @RequestBody @Valid PagamentoUpdateRequest pagamentoUpdateRequest) {
        return ok(new PagamentoResponse(pagamentoService.atualizar(id, mapper.toPagamento(pagamentoUpdateRequest, pagamentoService.buscarPorId(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Integer id) {
        pagamentoService.deletar(id);
        return noContent().build();
    }
}