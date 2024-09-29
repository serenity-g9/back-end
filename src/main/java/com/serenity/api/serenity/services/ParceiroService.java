package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Parceiro;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.ParceiroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParceiroService {

    private final ParceiroRepository parceiroRepository;
    private final UsuarioService usuarioService;

    public Parceiro cadastrar(Parceiro parceiro) {
        Usuario usuario = usuarioService.buscarPorId(parceiro.getUsuario().getId());
        parceiro.setUsuario(usuario);

        return parceiroRepository.save(parceiro);
    }

    public List<Parceiro> listar() {
        return parceiroRepository.findAll();
    }

    public Parceiro buscarPorId(Integer id) {
        return parceiroRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("parceiro"));
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        parceiroRepository.deleteById(id);
    }

    public Parceiro atualizar(Integer id, Parceiro parceiro) {
        buscarPorId(id);
        parceiro.setId(id);

        return parceiroRepository.save(parceiro);
    }

}
