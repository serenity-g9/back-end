package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoRequest;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoResponse;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoUpdateRequest;
import com.serenity.api.serenity.dtos.codigo.CodigoResponse;
import com.serenity.api.serenity.enums.StatusAgendamento;
import com.serenity.api.serenity.mappers.AgendamentoMapper;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.services.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/agendamentos", produces = {"application/json"})
@Tag(name = "CRUD-agendamento", description = "Controle de agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final AgendamentoMapper mapper;

    @Operation(summary = "Lista os agendamentos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "agendamentos encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar agendamentos"),
            @ApiResponse(responseCode = "204", description = "Nenhum agendamento cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> buscar() {
        List<AgendamentoResponse> agendamentoResponses = agendamentoService.listar().stream()
                .map(AgendamentoResponse::new)
                .collect(Collectors.toList());
        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de agendamentos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<AgendamentoResponse> cadastrar(@RequestBody @Valid AgendamentoRequest agendamentoRequest) {
        return created(null).body(new AgendamentoResponse(agendamentoService.cadastrar(mapper.toAgendamento(agendamentoRequest))));
    }

    @Operation(summary = "Procura um agendamento especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "agendamento encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar agendamento"),
            @ApiResponse(responseCode = "404", description = "agendamento não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new AgendamentoResponse(agendamentoService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um agendamento", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "agendamento não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid AgendamentoUpdateRequest agendamentoUpdateRequest) {
        return ok(new AgendamentoResponse(agendamentoService.atualizar(id, mapper.toAgendamento(agendamentoUpdateRequest, agendamentoService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um agendamento", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "agendamento não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable UUID id) {
        agendamentoService.deletar(id);
        return noContent().build();
    }

    @PatchMapping("/{id}/invite")
    public ResponseEntity<Void> convidar(@PathVariable UUID id, @RequestParam(name = "usuario") UUID idUsuario) {
        agendamentoService.convidar(id, idUsuario);
        return noContent().build();
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<Void> aceitar(@PathVariable UUID id) {
        agendamentoService.aceitar(id);
        return noContent().build();
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<Void> recusar(@PathVariable UUID id) {
        agendamentoService.recusar(id);
        return noContent().build();
    }

    @PatchMapping("/check-in")
    public ResponseEntity<CodigoResponse> realizarCheckin(
            @RequestParam
            @Pattern(regexp = "^[0-9]{6}$", message = "Código inválido. Utilize caracteres numéricos de 6 digitos")
            String digito
    ) {
        return ok(new CodigoResponse(agendamentoService.realizarCheckin(digito)));
    }

    @GetMapping("/status")
    public ResponseEntity<List<AgendamentoResponse>> buscarPorStatus(@RequestParam Integer status) {
        List<Agendamento> agendamentos = agendamentoService.buscarPorStatus(status);
        List<AgendamentoResponse> agendamentoResponses = agendamentos.stream()
                .map(AgendamentoResponse::new)
                .collect(Collectors.toList());
        return ok(agendamentoResponses);
    }
}