package com.serenity.api.serenity.controllers;


import com.serenity.api.serenity.dtos.autenticacao.LoginRequest;
import com.serenity.api.serenity.dtos.autenticacao.AccessTokenResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioRequest;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioUpdateRequest;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscar() {
        List<Usuario> usuarios = usuarioService.listar();
        return usuarios.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.status(201).body(usuarioService.cadastrar(usuarioRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Integer id, @RequestBody UsuarioUpdateRequest usuarioUpdateRequest) {
        return ResponseEntity.status(200).body(usuarioService.atualizar(id, usuarioUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(200).body(usuarioService.autenticar(loginRequest));
    }
}
