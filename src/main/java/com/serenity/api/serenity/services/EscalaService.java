package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.agendamento.AgendarBatchRequest;
import com.serenity.api.serenity.enums.StatusAgendamento;
import com.serenity.api.serenity.events.EscalaEvent;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.EscalaRepository;
import com.serenity.api.serenity.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EscalaService {

    private final EscalaRepository escalaRepository;
    private final AgendamentoService agendamentoService;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final DemandaService demandaService;

    public Escala cadastrar(Escala escala) {
        return escalaRepository.save(escala);
    }

    public List<Escala> listar() {
        return escalaRepository.findAll();
    }

    public Escala buscarPorId(UUID id) {
        return escalaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("escala"));
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        escalaRepository.deleteById(id);
    }

    public Escala atualizar(UUID id, Escala escala) {
        buscarPorId(id);
        escala.setId(id);

        return escalaRepository.save(escala);
    }


    public List<Agendamento> convidarPorEscala(UUID id, AgendarBatchRequest request) {
        int requestSize = request.emails().size();

        Escala escala = buscarPorId(id);
        List<Usuario> usuarios = usuarioRepository.findAllByEmailIn(request.emails());

        if (usuarios.size() != requestSize) {
            throw new NaoEncontradoException("usuario");
        }

        Pageable pageable = PageRequest.of(0, requestSize);
        List<Agendamento> agendamentos = escalaRepository.buscarAgendamentosDisponiveisPorEscala(escala.getId(), pageable).getContent();

        if (agendamentos.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }

        if (requestSize > agendamentos.size()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        }

        for (int i = 0; i < requestSize; i++) {
            Usuario iUsuario = usuarios.get(i);
            Agendamento iAgendamento = agendamentos.get(i);

            iAgendamento.setUsuario(iUsuario);
            emailService.queueEmail(
                    "zyalvlayz@gmail.com",
                    usuarios.get(i).getEmail(),
                    String.format("Convite - Evento %s", escala.getDemanda().getEvento().getNome()),
                    String.format("Olá %s", usuarios.get(i).getContato().getNome()),
                    iAgendamento
            );
        }

        emailService.process();
        return agendamentos;
    }

    public List<Escala> buscarPorDemanda(UUID idDemanda) {
        return escalaRepository.findAllByDemanda(demandaService.buscarPorId(idDemanda));
    }

    @EventListener
    @Async
    public void handleEscalaEvent(EscalaEvent event) {
        Escala escala = event.escala();
        List<Agendamento> agendamentos = new ArrayList<>();

        for (int i = 0; i < escala.getQtdColaborador(); i++) {
            Agendamento agendamento = new Agendamento();
            agendamento.setEscala(escala);
            agendamento.setHorarioEntrada(escala.getDemanda().getInicio());
            agendamento.setHorarioSaida(agendamento.getHorarioEntrada().plusHours(escala.getHorasJornada()));

            agendamentos.add(agendamento);
        }

        agendamentoService.cadastrarTodos(agendamentos);
    }
}
