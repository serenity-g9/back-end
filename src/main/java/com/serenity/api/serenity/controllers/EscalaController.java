package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoResponse;
import com.serenity.api.serenity.dtos.agendamento.AgendarBatchRequest;
import com.serenity.api.serenity.dtos.escala.EscalaRequest;
import com.serenity.api.serenity.dtos.escala.EscalaResponse;
import com.serenity.api.serenity.dtos.escala.EscalaUpdateRequest;
import com.serenity.api.serenity.mappers.EscalaMapper;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.services.EscalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/escalas", produces = {"application/json"})
@Tag(name = "CRUD-escalas", description = "Controle de escalas")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
public class EscalaController {

    private final EscalaService escalaService;
    private final EscalaMapper mapper;

    @Operation(summary = "Lista as escalas cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "escalas encontradas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar escalas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma escala cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<EscalaResponse>> buscar() {
        List<EscalaResponse> agendamentoResponses = escalaService.listar().stream()
                .map(EscalaResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de escalas", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<EscalaResponse> cadastrar(@RequestBody @Valid EscalaRequest escalaRequest) {
        return created(null).body(new EscalaResponse(escalaService.cadastrar(mapper.toEscala(escalaRequest))));
    }

    @Operation(summary = "Procura uma escala especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "escala encontrada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar escala"),
            @ApiResponse(responseCode = "404", description = "escala não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EscalaResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new EscalaResponse(escalaService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um escala", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "escala não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EscalaResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid EscalaUpdateRequest escalaUpdateRequest) {
        return ok(new EscalaResponse(escalaService.atualizar(id, mapper.toEscala(escalaUpdateRequest, escalaService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um escala", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "escala não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        escalaService.deletar(id);
        return noContent().build();
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<List<AgendamentoResponse>> convidarPorEscala(@PathVariable UUID id, @RequestBody AgendarBatchRequest usuariosId) {
        List<Agendamento> agendamentos = escalaService.convidarPorEscala(id, usuariosId);
        List<AgendamentoResponse> agendamentoResponses = agendamentos.stream()
                .map(AgendamentoResponse::new)
                .collect(Collectors.toList());
        return ok(agendamentoResponses);
    }

    @GetMapping("/{id}/demanda")
    public ResponseEntity<List<EscalaResponse>> buscarPorDemanda(@PathVariable UUID id) {
        List<EscalaResponse> escalaResponses = escalaService.buscarPorDemanda(id).stream()
                .map(EscalaResponse::new)
                .collect(Collectors.toList());

        return ok(escalaResponses);
    }
}
