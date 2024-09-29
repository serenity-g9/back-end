package com.serenity.api.serenity.services;

import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Registro;
import com.serenity.api.serenity.repositories.RegistroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

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

    public Registro buscarPorId(Integer id) {
        return registroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        registroRepository.deleteById(id);
    }

    public Registro atualizar(Integer id, Registro registro) {
        buscarPorId(id);
        registro.setId(id);

        return registroRepository.save(registro);
    }
}
