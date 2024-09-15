package com.serenity.api.serenity.controllers;


import com.serenity.api.serenity.dtos.usuario.LoginResponse;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscar() {
        List<Usuario> usuarios = usuarioService.listar();
        return usuarios.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioService.cadastrar(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id){
        return ResponseEntity.status(200).body(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody Usuario usuarioAtualizado) {
        return ResponseEntity.status(200).body(usuarioService.atualizar(id, usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        usuarioService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String email, @RequestParam String senha) {
        return ResponseEntity.status(204).body(usuarioService.login(email, senha));
    }
}
