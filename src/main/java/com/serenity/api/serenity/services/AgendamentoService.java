package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoRequest;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import com.serenity.api.serenity.repositories.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgendamentoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    public Agendamento cadastrar(AgendamentoRequest agendamentoRequest) {
        Optional<Colaborador> colaboradorOpt = colaboradorRepository.findById(agendamentoRequest.idColaborador());

        if (colaboradorOpt.isEmpty() || !eventoRepository.existsById(agendamentoRequest.idEvento())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        var agendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoRequest, agendamento);

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento buscarPorId(int id) {
        Optional<Agendamento> escala = agendamentoRepository.findById(id);

        if (escala.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return escala.get();
    }

    public Agendamento atualizar(int id, Agendamento agendamentoAtualizado) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        agendamentoAtualizado.setId(id);
        return agendamentoRepository.save(agendamentoAtualizado);
    }

    public void deletar (int id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        agendamentoRepository.deleteById(id);
    }
}
