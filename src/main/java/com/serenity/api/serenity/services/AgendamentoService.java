package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    public Agendamento cadastrar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento buscarPorId(int id) {
        return agendamentoRepository.findById(id).orElseThrow(() ->  new NaoEncontradoException("agendamento"));
    }

    public Agendamento atualizar(int id, Agendamento agendamento) {
        buscarPorId(id);
        agendamento.setId(id);

        return agendamentoRepository.save(agendamento);
    }

    public void deletar(int id) {
        buscarPorId(id);
        agendamentoRepository.deleteById(id);
    }
}
