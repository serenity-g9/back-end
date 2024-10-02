package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.pagamento.PagamentoRequest;
import com.serenity.api.serenity.dtos.pagamento.PagamentoResponse;
import com.serenity.api.serenity.dtos.pagamento.PagamentoUpdateRequest;
import com.serenity.api.serenity.mappers.PagamentoMapper;
import com.serenity.api.serenity.services.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/pagamentos",produces = {"application/json"})
@Tag(name = "CRUD-pagamentos", description = "Controle de pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final PagamentoMapper mapper;

    @Operation(summary = "Lista os pagamentos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "pagamentos encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar pagamentos"),
            @ApiResponse(responseCode = "204", description = "Nenhum pagamento cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<PagamentoResponse>> buscar() {
        List<PagamentoResponse> agendamentoResponses = pagamentoService.listar().stream()
                .map(PagamentoResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de pagamentos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<PagamentoResponse> cadastrar(@RequestBody @Valid PagamentoRequest pagamentoRequest) {
        return created(null).body(new PagamentoResponse(pagamentoService.cadastrar(mapper.toPagamento(pagamentoRequest))));
    }

    @Operation(summary = "Procura um pagamento especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "pagamento encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar pagamento"),
            @ApiResponse(responseCode = "404", description = "pagamento não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new PagamentoResponse(pagamentoService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um pagamentos", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "pagamento não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid PagamentoUpdateRequest pagamentoUpdateRequest) {
        return ok(new PagamentoResponse(pagamentoService.atualizar(id, mapper.toPagamento(pagamentoUpdateRequest, pagamentoService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um pagamentos", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "pagamento não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable UUID id) {
        pagamentoService.deletar(id);
        return noContent().build();
    }
}