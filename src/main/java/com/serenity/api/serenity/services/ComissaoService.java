package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Comissao;
import com.serenity.api.serenity.repositories.ComissaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ComissaoService {

    private final ComissaoRepository comissaoRepository;
    private final AgendamentoService agendamentoService;

    public List<Comissao> listar() {
        return comissaoRepository.findAll();
    }

    public Comissao cadastrar(Comissao comissao) {

        comissao.setEfetuado(false);

        return comissaoRepository.save(comissao);
    }

    public Comissao buscarPorId(int id) {
        return comissaoRepository.findById(id).orElseThrow(() ->  new NaoEncontradoException("comissao"));
    }

    public Comissao atualizar(int id, Comissao comissao) {
        buscarPorId(id);
        comissao.setId(id);

        return comissaoRepository.save(comissao);
    }

    public void deletar (int id) {
        buscarPorId(id);
        comissaoRepository.deleteById(id);
    }
}
