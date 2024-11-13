package com.serenity.api.serenity.services;

import com.serenity.api.serenity.events.GerarCodigoEvent;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Codigo;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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

    public void agendar(UUID id, UUID idUsuario) {
        Agendamento agendamento = buscarPorId(id);
        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        agendamento.setUsuario(usuario);
        atualizar(agendamento.getId(), agendamento);
    }

    public Codigo realizarCheckin(String sequencia) {
        return codigoService.confirmarCodigo(sequencia);
    }

    @EventListener
    public void handleGerarCodigoEvent(GerarCodigoEvent event) {
        Agendamento agendamento = event.agendamento();
        Codigo codigo = codigoService.gerarCodigo();
        agendamento.setCodEntrada(codigo);

        codigoService.cadastrar(codigo);
    }
}
