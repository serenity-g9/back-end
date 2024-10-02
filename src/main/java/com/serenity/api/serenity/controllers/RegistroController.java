package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.registro.RegistroRequest;
import com.serenity.api.serenity.dtos.registro.RegistroResponse;
import com.serenity.api.serenity.mappers.RegistroMapper;
import com.serenity.api.serenity.services.RegistroService;
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
@RequestMapping(value = "/registros", produces = {"application/json"})
@Tag(name = "CRUD-Registro", description = "Controle de registros")
@RequiredArgsConstructor
public class RegistroController {

    private final RegistroService registroService;
    private final RegistroMapper mapper;

    @Operation(summary = "Lista os registros cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "registros encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar registros"),
            @ApiResponse(responseCode = "204", description = "Nenhum registro cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<RegistroResponse>> buscar() {
        List<RegistroResponse> agendamentoResponses = registroService.listar().stream()
                .map(RegistroResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de registros", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<RegistroResponse> cadastrar(@RequestBody @Valid RegistroRequest registroRequest) {
        return created(null).body(new RegistroResponse(registroService.cadastrar(mapper.toRegistro(registroRequest))));
    }

    @Operation(summary = "Procura um registro especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "registro encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar registro"),
            @ApiResponse(responseCode = "404", description = "registro não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new RegistroResponse(registroService.buscarPorId(id)));
    }

    @Operation(summary = "Deleta um registros", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "registro não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        registroService.deletar(id);
        return noContent().build();
    }
}
