package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.autenticacao.AccessTokenResponse;
import com.serenity.api.serenity.dtos.autenticacao.LoginRequest;
import com.serenity.api.serenity.dtos.usuario.SenhaPatchRequest;
import com.serenity.api.serenity.dtos.usuario.UsuarioRequest;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioUpdateRequest;
import com.serenity.api.serenity.mappers.UsuarioMapper;
import com.serenity.api.serenity.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/usuarios", produces = {"application/json"})
@Tag(name = "CRUD-emails", description = "Controle de emails")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @Operation(summary = "Lista os emails cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuarios encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar emails"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuario cadastrado")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> buscar() {
        List<UsuarioResponse> agendamentoResponses = usuarioService.listar().stream()
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());
        return ok(agendamentoResponses);
    }

    @Operation(summary = "Realiza criação de emails", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        return created(null).body(new UsuarioResponse(usuarioService.cadastrar(mapper.toUsuario(usuarioRequest))));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponse> cadastroPublico(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        return created(null).body(new UsuarioResponse(usuarioService.cadastrar(mapper.toUsuario(usuarioRequest))));
    }

    @Operation(summary = "Procura um usuario especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar usuario"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new UsuarioResponse(usuarioService.buscarPorId(id)));
    }

    @Operation(summary = "Atualiza um usuario", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest) {
        return ok(new UsuarioResponse(usuarioService.atualizar(id, mapper.toUsuario(usuarioUpdateRequest, usuarioService.buscarPorId(id)))));
    }

    @Operation(summary = "Deleta um usuario", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        usuarioService.deletar(id);
        return noContent().build();
    }

    @Operation(summary = "Faz o login de um usuario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Login efetuado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao logar"),
            @ApiResponse(responseCode = "401", description = "Usuario não existe")
    })

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ok(usuarioService.autenticar(loginRequest));
    }

    @Operation(summary = "Atualiza a senha de um usuario", method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")
    })
    @PatchMapping("/{id}/trocar-senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable UUID id, @RequestBody @Valid SenhaPatchRequest senhaPatchRequest) {
        usuarioService.trocarSenha(id, senhaPatchRequest);
        return noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<AccessTokenResponse> buscarUsuarioAtual(Authentication request) {
        return ok(usuarioService.buscarUsuarioAtual(request));
    }
}
