package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.evento.EventoRequest;
import com.serenity.api.serenity.dtos.evento.EventoResponse;
import com.serenity.api.serenity.dtos.evento.EventoUpdateRequest;
import com.serenity.api.serenity.mappers.EventoMapper;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.services.ImagemService;
import com.serenity.api.serenity.services.EventoService;
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

@RestController
@RequestMapping(value = "/eventos", produces = {"application/json"})
@Tag(name = "CRUD-eventos", description = "Controle de eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;
    private final EventoMapper mapper;
    private final ImagemService imagemService;

    @Operation(summary = "Lista os eventos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "eventos encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar eventos"),
            @ApiResponse(responseCode = "204", description = "Nenhum evento cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<EventoResponse>> buscar() {
        List<EventoResponse> agendamentoResponses = eventoService.listar().stream()
                .map(EventoResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de eventos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<EventoResponse> cadastrar(@RequestBody @Valid EventoRequest eventoRequest) {
        return created(null).body(new EventoResponse(eventoService.cadastrar(mapper.toEvento(eventoRequest))));
    }

    @Operation(summary = "Procura um evento especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "evento encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar evento"),
            @ApiResponse(responseCode = "404", description = "evento não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new EventoResponse(eventoService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um evento", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "evento não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventoResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid EventoUpdateRequest eventoUpdateRequest) {
        return ok(new EventoResponse(eventoService.atualizar(id, mapper.toEvento(eventoUpdateRequest, eventoService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um evento", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "evento não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        eventoService.deletar(id);
        return noContent().build();
    }

    @PatchMapping("/{id}/upload")
    public ResponseEntity<Void> anexarImagem(@PathVariable UUID id, @RequestParam MultipartFile img) {
        Evento evento = eventoService.buscarPorId(id);

        evento.setImagem(imagemService.cadastrar(img));

        eventoService.atualizar(id, evento);

        return noContent().build();
    }
}
