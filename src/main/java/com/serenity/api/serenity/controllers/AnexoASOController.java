package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.anexosASO.AnexoASORequest;
import com.serenity.api.serenity.dtos.anexosASO.AnexoASOResponse;
import com.serenity.api.serenity.dtos.anexosASO.AnexoASOUpdateRequest;
import com.serenity.api.serenity.mappers.AnexoASOMapper;
import com.serenity.api.serenity.models.AnexoASO;
import com.serenity.api.serenity.services.AnexoASOService;
import com.serenity.api.serenity.services.ImagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping(value = "/AnexoASOs", produces = {"application/json"})
@Tag(name = "CRUD-AnexoASOs", description = "Controle de AnexoASOs")
@RequiredArgsConstructor

public class AnexoASOController {
    private final AnexoASOService anexoASOService;
    private final AnexoASOMapper mapper;
//    private final ImagemService imagemService;

    @Operation(summary = "Lista os AnexoASOs cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "AnexoASOs encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar AnexoASOs"),
            @ApiResponse(responseCode = "204", description = "Nenhum AnexoASO cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<AnexoASOResponse>> buscar() {
        List<AnexoASOResponse> agendamentoResponses = anexoASOService.listar().stream()
                .map(AnexoASOResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de AnexoASOs", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<AnexoASOResponse> cadastrar(@RequestBody @Valid AnexoASORequest anexoASORequest) {
        return created(null).body(new AnexoASOResponse(anexoASOService.cadastrar(mapper.toAnexoASO(anexoASORequest))));
    }

    @Operation(summary = "Procura um AnexoASO especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "AnexoASO encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar AnexoASO"),
            @ApiResponse(responseCode = "404", description = "AnexoASO não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnexoASOResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new AnexoASOResponse(anexoASOService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um AnexoASO", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "AnexoASO não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnexoASOResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid AnexoASOUpdateRequest AnexoASOUpdateRequest) {
        return ok(new AnexoASOResponse(anexoASOService.atualizar(id, mapper.UpdateToAnexoASO(AnexoASOUpdateRequest, anexoASOService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um AnexoASO", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "AnexoASO não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        anexoASOService.deletar(id);
        return noContent().build();
    }

    @PatchMapping("/{id}/upload")
    public ResponseEntity<Void> anexarImagem(@PathVariable UUID id, @RequestParam MultipartFile img) {
        AnexoASO AnexoASO = anexoASOService.buscarPorId(id);

//        AnexoASO.setImagem(imagemService.cadastrar(img));

        anexoASOService.atualizar(id, AnexoASO);

        return noContent().build();
    }
}
