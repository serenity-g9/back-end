package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.demanda.DemandaRequest;
import com.serenity.api.serenity.dtos.demanda.DemandaResponse;
import com.serenity.api.serenity.dtos.demanda.DemandaUpdateRequest;
import com.serenity.api.serenity.mappers.DemandaMapper;
import com.serenity.api.serenity.services.DemandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/demandas", produces = {"application/json"})
@Tag(name = "CRUD-demandas", description = "Controle de demandas")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
public class DemandaController {

    private final DemandaService demandaService;
    private final DemandaMapper mapper;

    @Operation(summary = "Lista os demandas cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "demandas encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar demandas"),
            @ApiResponse(responseCode = "204", description = "Nenhum demanda cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<DemandaResponse>> buscar() {
        List<DemandaResponse> agendamentoResponses = demandaService.listar().stream()
                .map(DemandaResponse::new)
                .toList();

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de demandas", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<DemandaResponse> cadastrar(@RequestBody @Valid DemandaRequest demandaRequest) {
        return ResponseEntity.created(null).body(new DemandaResponse(demandaService.cadastrar(mapper.toDemanda(demandaRequest))));
    }

    @Operation(summary = "Procura uma demanda especifica", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "demanda encontrada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar demanda"),
            @ApiResponse(responseCode = "404", description = "Demanda não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DemandaResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new DemandaResponse(demandaService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza uma demanda", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "Demanda não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DemandaResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid DemandaUpdateRequest demandaUpdateRequest) {
        return ok(new DemandaResponse(demandaService.atualizar(id, mapper.toDemanda(demandaUpdateRequest, demandaService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta uma demanda", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "Demanda não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        demandaService.deletar(id);
        return noContent().build();
    }
}
