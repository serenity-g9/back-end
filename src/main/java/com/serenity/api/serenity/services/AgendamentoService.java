package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoRequest;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoResponse;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import com.serenity.api.serenity.repositories.EscalaRepository;
import com.serenity.api.serenity.repositories.RegistroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgendamentoService {
    @Autowired
    private EscalaRepository escalaRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private RegistroService registroService;
    @Autowired
    private RegistroRepository registroRepository;

    public List<AgendamentoResponse> listar() {
        return agendamentoRepository.findAll().stream()
                .map(agendamento -> new AgendamentoResponse(agendamento))
                .collect(Collectors.toList());
    }

    public Agendamento cadastrar(AgendamentoRequest agendamentoRequest) {
        Optional<Colaborador> colaboradorOpt = colaboradorRepository.findById(agendamentoRequest.idColaborador());
        Optional<Escala> escalaOpt = escalaRepository.findById(agendamentoRequest.idEscala());

        if (colaboradorOpt.isEmpty() || escalaOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        var agendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoRequest, agendamento);
        agendamento.setEscala(escalaOpt.get());

        return agendamentoRepository.save(agendamento);
    }

    public AgendamentoResponse buscarPorId(int id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);

        if (agendamento.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return new AgendamentoResponse(agendamento.get());
    }

    public AgendamentoResponse atualizar(int id, Agendamento agendamentoAtualizado) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        agendamentoAtualizado.setId(id);
        return new AgendamentoResponse(agendamentoRepository.save(agendamentoAtualizado));
    }

    public void deletar (int id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        agendamentoRepository.deleteById(id);
    }
}
