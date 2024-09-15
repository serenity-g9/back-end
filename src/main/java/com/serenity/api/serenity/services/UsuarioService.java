package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.usuario.LoginResponse;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        usuario.setId(null);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return usuario.get();
    }

    public void deletar(Integer id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Integer id, Usuario usuario) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return usuarioRepository.save(usuario);
    }

    public LoginResponse login(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        }

        return new LoginResponse(usuario.get());
    }
}
