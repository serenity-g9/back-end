package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.formulario.*;
import com.serenity.api.serenity.mappers.FormularioMapper;
import com.serenity.api.serenity.services.FormularioService;
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
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/forms", produces = {"application/json"})
@RequiredArgsConstructor
@Tag(name = "CRUD-formularios", description = "Controle de formulários")
@SecurityRequirement(name = "Bearer")
public class FormularioController {

    @Operation(summary = "Lista os respostas cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "respostas encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar respostas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma resposta cadastrado")
    })
    @GetMapping("/responses/{id}/google")
    public ResponseEntity<List<RespostaUsuario>> getRespostas(@PathVariable String id) {
        return ok(FormularioService.getRespostas(id));
    }

    @Operation(summary = "Lista os respostas cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "respostas encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar respostas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma resposta cadastrado")
    })
    @GetMapping("/{id}/google")
    public ResponseEntity<Map<String, Object>> getRespostasPorQuestao(@PathVariable String id) {
        return ok(formularioService.getRespostasPorQuestao(id));
    }

    @GetMapping("/google")
    public ResponseEntity<List<GoogleForm>> getGoogleForms() {
        return ok(FormularioService.getGoogleForms());
    }

    @PostMapping("/google")
    public ResponseEntity<List<FormularioResponse>> integrarForms() {
        List<FormularioResponse> formularioResponses = formularioService.integrarForms().stream()
                .map(FormularioResponse::new)
                .collect(Collectors.toList());

        return created(null).body(formularioResponses);
    }

    @Operation(summary = "Lista os perguntas cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "perguntas encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar perguntas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma pergunta cadastrado")
    })
        @GetMapping("/questions/{id}/google")
    public ResponseEntity<List<Questao>> getQuestoes(@PathVariable String id) {
        return ok(FormularioService.getQuestoes(id));
    }

    private final FormularioService formularioService;
    private final FormularioMapper mapper;

    @Operation(summary = "Lista os formularios cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "formularios encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar formularios"),
            @ApiResponse(responseCode = "204", description = "Nenhum formulario cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<FormularioResponse>> buscar() {
        List<FormularioResponse> agendamentoResponses = formularioService.listar().stream()
                .map(FormularioResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de formularios", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<FormularioResponse> cadastrar(@RequestBody @Valid FormularioRequest formularioRequest) {
        return created(null).body(new FormularioResponse(formularioService.cadastrar(mapper.toFormulario(formularioRequest))));
    }

    @Operation(summary = "Procura um formulario especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "formulario encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar formulario"),
            @ApiResponse(responseCode = "404", description = "formulario não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FormularioResponse> buscarPorId(@PathVariable String id) {
        return ok(new FormularioResponse(formularioService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um formulario", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "formulario não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FormularioResponse> atualizar(@PathVariable String id, @RequestBody @Valid FormularioUpdateRequest formularioUpdateRequest) {
        return ok(new FormularioResponse(formularioService.atualizar(id, mapper.toFormulario(formularioUpdateRequest, formularioService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um formulario", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "formulario não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        formularioService.deletar(id);
        return noContent().build();
    }
}
