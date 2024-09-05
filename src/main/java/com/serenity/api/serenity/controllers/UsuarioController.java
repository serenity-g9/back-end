package com.serenity.api.serenity.controllers;


import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscar() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
        usuario.setId(null);
        return ResponseEntity.status(201).body(usuarioRepository.save(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(usuario.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody Usuario usuarioAtualizado){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.status(404).build();
        }
        usuarioAtualizado.setId(id);
        Usuario usuarioRetorno = usuarioRepository.save(usuarioAtualizado);
        return  ResponseEntity.status(200).body(usuarioRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar (@PathVariable int id){
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> logar( @RequestParam String email, @RequestParam String senha){
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(usuario.get());

    }











}
