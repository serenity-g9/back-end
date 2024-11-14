package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.comissao.ComissaoRequest;
import com.serenity.api.serenity.dtos.comissao.ComissaoResponse;
import com.serenity.api.serenity.dtos.comissao.ComissaoUpdateRequest;
import com.serenity.api.serenity.mappers.ComissaoMapper;
import com.serenity.api.serenity.services.ComissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping(value = "/comissoes", produces = {"application/json"})
@Tag(name = "CRUD-comissões", description = "Controle de comissoes")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
public class ComissaoController {
    private final ComissaoService comissaoService;
    private final ComissaoMapper mapper;

    @Operation(summary = "Lista as comissaões cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "comissoes encontradas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar comissoes"),
            @ApiResponse(responseCode = "204", description = "Nenhuma comissao cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<ComissaoResponse>> buscar() {
        List<ComissaoResponse> agendamentoResponses = comissaoService.listar().stream()
                .map(ComissaoResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de comissões", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<ComissaoResponse> cadastrar(@RequestBody @Valid ComissaoRequest comissaoRequest) {
        return created(null).body(new ComissaoResponse(comissaoService.cadastrar(mapper.toComissao(comissaoRequest))));
    }

    @Operation(summary = "Procura uma comissão especifica", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "comissao encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar comissao"),
            @ApiResponse(responseCode = "404", description = "comissao não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ComissaoResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new ComissaoResponse(comissaoService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza uma comissão", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "comissao não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ComissaoResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid ComissaoUpdateRequest comissaoUpdateRequest) {
        return ok(new ComissaoResponse(comissaoService.atualizar(id, mapper.toComissao(comissaoUpdateRequest, comissaoService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta uma comissão", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "comissao não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable UUID id) {
        comissaoService.deletar(id);
        return noContent().build();
    }
}