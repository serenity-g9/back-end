package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.agendamento.AgendarBatchRequest;
import com.serenity.api.serenity.enums.StatusAgendamento;
import com.serenity.api.serenity.events.GerarCodigoEvent;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Codigo;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioService usuarioService;
    private final CodigoService codigoService;

    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> listarPorUsuario(UUID idUsuario) {
        return agendamentoRepository.findAllByUsuarioAndDeletedAtIsNull(usuarioService.buscarPorId(idUsuario));
    }

    public Agendamento cadastrar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> cadastrarTodos(List<Agendamento> agendamentos) {
        return agendamentoRepository.saveAll(agendamentos);
    }

    public Agendamento buscarPorId(UUID id) {
        return agendamentoRepository.findById(id).orElseThrow(() ->  new NaoEncontradoException("agendamento"));
    }

    public Agendamento atualizar(UUID id, Agendamento agendamento) {
        buscarPorId(id);
        agendamento.setId(id);

        return agendamentoRepository.save(agendamento);
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        agendamentoRepository.deleteById(id);
    }

    public Agendamento convidar(UUID id, UUID idUsuario) {
        Agendamento agendamento = buscarPorId(id);
        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        agendamento.setUsuario(usuario);
        return atualizar(agendamento.getId(), agendamento);
    }

    public Agendamento aceitar(UUID id) {
        Agendamento agendamento = buscarPorId(id);

        if (agendamento.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "O agendamento não possui um usuário cadastrado");
        }

        if (agendamento.getHorarioInvitacaoAceito() != null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "O agendamento já foi aceito");
        }

        agendamento.setHorarioInvitacaoAceito(LocalDateTime.now());
        return atualizar(agendamento.getId(), agendamento);
    }

    public Agendamento recusar(UUID id) {
        Agendamento agendamento = buscarPorId(id);

        if (agendamento.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "O agendamento não possui um usuário cadastrado");
        }

        if (agendamento.getHorarioInvitacaoAceito() != null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "O agendamento já foi aceito");
        }

        agendamento.setUsuario(null);
        return atualizar(agendamento.getId(), agendamento);
    }

    public Codigo realizarCheckin(String digito) {
        return codigoService.confirmarCodigo(digito);
    }

    public List<Agendamento> buscarPorStatus(Integer status) {
        return agendamentoRepository.buscarPorStatus(status);
    }

    public List<Agendamento> buscarPorEscala(UUID id) {
        return agendamentoRepository.findAllByEscalaId(id);
    }

    public List<Agendamento> atualizarTodos(List<Agendamento> agendamentos) {

        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getId() == null) throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }

        return agendamentoRepository.saveAll(agendamentos);
    }

    @EventListener
    public void handleGerarCodigoEvent(GerarCodigoEvent event) {
        Agendamento agendamento = event.agendamento();

        if (agendamento.getHorarioInvitacaoAceito() == null || agendamento.getUsuario() == null) {
            return;
        }

        Codigo codigo = codigoService.gerarCodigo();
        agendamento.setCodEntrada(codigo);

        codigoService.cadastrar(codigo);
    }

    public Agendamento buscarPorDigito(String digito) {
        Codigo codigo = codigoService.buscarPorDigito(digito);
        return agendamentoRepository.findAgendamentoByCodEntrada(codigo);
    }
}
