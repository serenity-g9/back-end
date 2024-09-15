package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.usuario.LoginResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioRequest;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioUpdateRequest;
import com.serenity.api.serenity.enums.TipoLogin;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import com.serenity.api.serenity.repositories.ParceiroRepository;
import com.serenity.api.serenity.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ParceiroRepository parceiroRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public UsuarioResponse cadastrar(UsuarioRequest usuarioRequest) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRequest,usuario);
        return new UsuarioResponse(usuarioRepository.save(usuario));
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

    public UsuarioResponse atualizar(Integer id, UsuarioUpdateRequest usuarioUpdateRequest) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioUpdateRequest, usuario);
        usuario.setId(id);
        return new UsuarioResponse(usuarioRepository.save(usuario));
    }

    public LoginResponse login(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        }

        Set<TipoLogin> tipoLoginSet = new HashSet<>();

        if (parceiroRepository.existsByUsuario(usuarioOpt.get())) tipoLoginSet.add(TipoLogin.PARCEIRO);
        if (colaboradorRepository.existsByUsuario(usuarioOpt.get())) tipoLoginSet.add(TipoLogin.COLABORADOR);

        return new LoginResponse(usuarioOpt.get(), tipoLoginSet);
    }
}
