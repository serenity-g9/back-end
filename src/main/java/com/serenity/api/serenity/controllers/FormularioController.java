package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.formulario.Questao;
import com.serenity.api.serenity.dtos.formulario.RespostaUsuario;
import com.serenity.api.serenity.services.FormularioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/forms", produces = {"application/json"})
@Tag(name = "CRUD-formularios", description = "Controle de formulários")
public class FormularioController {

    @Operation(summary = "Lista os respostas cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "respostas encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar respostas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma resposta cadastrado")
    })
    @GetMapping("/respostas/{id}")
    public ResponseEntity<List<RespostaUsuario>> getRespostas(@PathVariable String id) {
        return ok(FormularioService.getRespostas(id));
    }

    @Operation(summary = "Lista os perguntas cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "perguntas encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar perguntas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma pergunta cadastrado")
    })
    @GetMapping("/questoes/{id}")
    public ResponseEntity<List<Questao>> getQuestoes(@PathVariable String id) {
        return ok(FormularioService.getQuestoes(id));
    }
}
