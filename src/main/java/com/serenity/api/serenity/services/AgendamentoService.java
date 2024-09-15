package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoRequest;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoResponse;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoUpdateRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import com.serenity.api.serenity.repositories.EscalaRepository;
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

    public List<AgendamentoResponse> listar() {
        return agendamentoRepository.findAll().stream()
                .map(agendamento -> new AgendamentoResponse(agendamento, registroService.listarPorAgendamento(agendamento)))
                .collect(Collectors.toList());
    }

    public AgendamentoResponse cadastrar(AgendamentoRequest agendamentoRequest) {
        Optional<Colaborador> colaboradorOpt = colaboradorRepository.findById(agendamentoRequest.idColaborador());
        Optional<Escala> escalaOpt = escalaRepository.findById(agendamentoRequest.idEscala());

        if (colaboradorOpt.isEmpty() || escalaOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        var agendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoRequest, agendamento);
        agendamento.setEscala(escalaOpt.get());

        return new AgendamentoResponse(agendamentoRepository.save(agendamento), registroService.listarPorAgendamento(agendamento));
    }

    public AgendamentoResponse buscarPorId(int id) {
        Optional<Agendamento> agendamentoOpt = agendamentoRepository.findById(id);

        if (agendamentoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return new AgendamentoResponse(agendamentoOpt.get(), registroService.listarPorAgendamento(agendamentoOpt.get()));
    }

    public AgendamentoResponse atualizar(int id, AgendamentoUpdateRequest agendamentoUpdateRequest) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        var agendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoUpdateRequest, agendamento);
        agendamento.setId(id);

        return new AgendamentoResponse(agendamentoRepository.save(agendamento), registroService.listarPorAgendamento(agendamento));
    }

    public void deletar (int id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        agendamentoRepository.deleteById(id);
    }
}
