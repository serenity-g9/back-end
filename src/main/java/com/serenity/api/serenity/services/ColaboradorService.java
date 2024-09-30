package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioService usuarioService;

    public Colaborador cadastrar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> listar() {
        return colaboradorRepository.findAll();
    }

    public Colaborador buscarPorId(UUID id) {
        return colaboradorRepository.findById(id).orElseThrow(() ->  new NaoEncontradoException("colaborador"));
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        colaboradorRepository.deleteById(id);
    }

    public Colaborador atualizar(UUID id, Colaborador colaborador) {
        buscarPorId(id);
        colaborador.setId(id);

        return colaboradorRepository.save(colaborador);
    }

}
