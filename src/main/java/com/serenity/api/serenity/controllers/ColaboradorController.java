package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.colaborador.ColaboradorRequest;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorResponse;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorUpdateRequest;
import com.serenity.api.serenity.mappers.ColaboradorMapper;
import com.serenity.api.serenity.services.ColaboradorService;
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
@RequestMapping(value = "/colaboradores", produces = {"application/json"})
@Tag(name = "CRUD-colaboradores", description = "Controle de colaboradores")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ColaboradorService colaboradorService;
    private final ColaboradorMapper mapper;

    @Operation(summary = "Lista os colaboradores cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "colaboradores encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar colaboradores"),
            @ApiResponse(responseCode = "204", description = "Nenhum colaborador cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<ColaboradorResponse>> buscar() {
        List<ColaboradorResponse> agendamentoResponses = colaboradorService.listar().stream()
                .map(ColaboradorResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de colaboradores", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<ColaboradorResponse> cadastrar(@RequestBody @Valid ColaboradorRequest colaboradorRequest) {
        return created(null).body(new ColaboradorResponse(colaboradorService.cadastrar(mapper.toColaborador(colaboradorRequest))));
    }

    @Operation(summary = "Procura um colaborador especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "colaborador encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar colaborador"),
            @ApiResponse(responseCode = "404", description = "colaborador não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> buscarPorId(@PathVariable UUID id) {
        System.out.println(colaboradorService.buscarPorId(id));
        return ok(new ColaboradorResponse(colaboradorService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um colaborador", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "colaborador não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid ColaboradorUpdateRequest colaboradorUpdateRequest) {
        return ok(new ColaboradorResponse(colaboradorService.atualizar(id, mapper.toColaborador(colaboradorUpdateRequest, colaboradorService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um colaborador", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "colaborador não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        colaboradorService.deletar(id);
        return noContent().build();
    }
}
