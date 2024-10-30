package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Registro;
import com.serenity.api.serenity.repositories.RegistroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistroService {

    private final RegistroRepository registroRepository;
    private final AgendamentoService agendamentoService;

    public Registro cadastrar(Registro registro) {
        if (registroRepository.existsByTipoRegistro(registro.getTipoRegistro())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return registroRepository.save(registro);
    }

    public List<Registro> listar() {
        return registroRepository.findAll();
    }

    public List<Registro> listarPorAgendamento(Agendamento agendamento) {
        return registroRepository.findAllByAgendamento(agendamento);
    }

    public Registro buscarPorId(UUID id) {
        return registroRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("registro"));
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        registroRepository.deleteById(id);
    }

    public Registro atualizar(UUID id, Registro registro) {
        buscarPorId(id);
        registro.setId(id);

        return registroRepository.save(registro);
    }
}
