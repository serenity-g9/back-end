package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.parceiro.ParceiroRequest;
import com.serenity.api.serenity.dtos.parceiro.ParceiroResponse;
import com.serenity.api.serenity.dtos.parceiro.ParceiroUpdateRequest;
import com.serenity.api.serenity.mappers.ParceiroMapper;
import com.serenity.api.serenity.services.ParceiroService;
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
@RequestMapping(value = "/parceiros", produces = {"application/json"})
@Tag(name = "CRUD-parceiros", description = "Controle de parceiros")
@RequiredArgsConstructor
public class ParceiroController {

    private final ParceiroService parceiroService;
    private final ParceiroMapper mapper;

    @Operation(summary = "Lista os pareceiros cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "pareceiros encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar pareceiros"),
            @ApiResponse(responseCode = "204", description = "Nenhum pareceiro cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<ParceiroResponse>> buscar() {
        List<ParceiroResponse> agendamentoResponses = parceiroService.listar().stream()
                .map(ParceiroResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de pareceiros", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<ParceiroResponse> cadastrar(@RequestBody @Valid ParceiroRequest parceiroRequest) {
        return created(null).body(new ParceiroResponse(parceiroService.cadastrar(mapper.toParceiro(parceiroRequest))));
    }

    @Operation(summary = "Procura um pareceiro especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "pareceiro encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar pareceiro"),
            @ApiResponse(responseCode = "404", description = "pareceiro não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParceiroResponse> buscarPorId(@PathVariable UUID id) {
        System.out.println(parceiroService.buscarPorId(id));
        return ok(new ParceiroResponse(parceiroService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um pareceiros", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "pareceiro não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ParceiroResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid ParceiroUpdateRequest parceiroUpdateRequest) {
        return ok(new ParceiroResponse(parceiroService.atualizar(id, mapper.toParceiro(parceiroUpdateRequest, parceiroService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um pareceiros", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "pareceiro não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        parceiroService.deletar(id);
        return noContent().build();
    }
}
