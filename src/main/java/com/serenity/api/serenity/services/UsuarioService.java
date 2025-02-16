package com.serenity.api.serenity.services;

import com.serenity.api.serenity.configuration.security.jwt.GerenciadorTokenJwt;
import com.serenity.api.serenity.dtos.autenticacao.AccessTokenResponse;
import com.serenity.api.serenity.dtos.autenticacao.LoginRequest;
import com.serenity.api.serenity.dtos.usuario.SenhaPatchRequest;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public Usuario cadastrar(Usuario usuario) {

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        System.out.println(usuario);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("usuario"));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new NaoEncontradoException("usuario"));
    }

    public void deletar(UUID id) {
        buscarPorId(id);

        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(UUID id, Usuario usuario) {
        buscarPorId(id);
        usuario.setId(id);

        return usuarioRepository.save(usuario);
    }

    public AccessTokenResponse autenticar(LoginRequest loginRequest) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = usuarioRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new NaoEncontradoException("usuario"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return new AccessTokenResponse(usuarioAutenticado, token);
    }

    public void trocarSenha(UUID id, SenhaPatchRequest senhaPatchRequest) {
        Usuario usuario = buscarPorId(id);
        usuario.setId(id);

        String senhaCriptografada = passwordEncoder.encode(senhaPatchRequest.senha());
        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);
    }
}
