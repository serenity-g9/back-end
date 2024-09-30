package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.autenticacao.AccessTokenResponse;
import com.serenity.api.serenity.dtos.autenticacao.LoginRequest;
import com.serenity.api.serenity.dtos.usuario.SenhaPatchRequest;
import com.serenity.api.serenity.dtos.usuario.UsuarioRequest;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioUpdateRequest;
import com.serenity.api.serenity.mappers.UsuarioMapper;
import com.serenity.api.serenity.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> buscar() {
        List<UsuarioResponse> agendamentoResponses = usuarioService.listar().stream()
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        return created(null).body(new UsuarioResponse(usuarioService.cadastrar(mapper.toUsuario(usuarioRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new UsuarioResponse(usuarioService.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest) {
        return ok(new UsuarioResponse(usuarioService.atualizar(id, mapper.toUsuario(usuarioUpdateRequest, usuarioService.buscarPorId(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        usuarioService.deletar(id);
        return noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ok(usuarioService.autenticar(loginRequest));
    }

    @PatchMapping("/{id}/trocar-senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable UUID id, @RequestBody @Valid SenhaPatchRequest senhaPatchRequest) {
        usuarioService.trocarSenha(id, senhaPatchRequest);
        return noContent().build();
    }
}
