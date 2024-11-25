package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.evento.EventoDemandasResponse;
import com.serenity.api.serenity.dtos.evento.EventoRequest;
import com.serenity.api.serenity.dtos.evento.EventoResponse;
import com.serenity.api.serenity.dtos.evento.EventoUpdateRequest;
import com.serenity.api.serenity.mappers.EventoMapper;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.services.AnexoService;
import com.serenity.api.serenity.services.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/eventos", produces = {"application/json"})
@Tag(name = "CRUD-eventos", description = "Controle de eventos")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
public class EventoController {

    private final EventoService eventoService;
    private final EventoMapper mapper;
    private final AnexoService anexoService;

    @Operation(summary = "Lista os eventos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "eventos encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar eventos"),
            @ApiResponse(responseCode = "204", description = "Nenhum evento cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<EventoResponse>> buscar() {
        List<EventoResponse> eventoResponses = eventoService.listar()
                .stream()
                .map(EventoResponse::new)
                .collect(Collectors.toList());

        return ok(eventoResponses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventoResponse>> buscarPorNome(@RequestParam String nome) {
        List<EventoResponse> eventoResponses = eventoService.buscarPorNome(nome)
                .stream()
                .map(EventoResponse::new)
                .toList();

        return ok(eventoResponses);
    }

    @Operation(summary = "Realiza criação de eventos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<EventoResponse> cadastrar(@RequestPart("data") EventoRequest eventoRequest, @RequestPart(value = "file", required = false) MultipartFile file) {
        Evento evento = eventoService.cadastrar(mapper.toEvento(eventoRequest));
        if (file != null) evento.setImagem(anexoService.cadastrar(file, 0));
        eventoService.atualizar(evento.getId(), evento);

        return created(null).body(new EventoResponse(evento));
    }

    @PatchMapping("/{id}/upload")
    public ResponseEntity<Void> atualizarImagem(@PathVariable UUID id, @RequestParam MultipartFile img) {
        Evento evento = eventoService.buscarPorId(id);

        if (evento.getImagem() == null) {
            evento.setImagem(anexoService.cadastrar(img, 0));
            eventoService.atualizar(id, evento);
        } else {
            anexoService.atualizar(img, evento.getImagem().getId());
        }


        return noContent().build();
    }

    @Operation(summary = "Procura um evento especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "evento encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar evento"),
            @ApiResponse(responseCode = "404", description = "evento não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventoDemandasResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new EventoDemandasResponse(eventoService.buscarPorId(id)));
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

    @PatchMapping("/restaurar")
    public ResponseEntity<EventoResponse> restaurar() {
        return ok(new EventoResponse(eventoService.restaurarEvento()));
    }

    @PostMapping("/export")
    public ResponseEntity<String> exportar(@RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fim, @RequestParam Integer quantidade) {
        String csv = eventoService.exportar(inicio, fim, quantidade);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "export.csv");

        return ok().headers(headers).body(csv);
    }
}
