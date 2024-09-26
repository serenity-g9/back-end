package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.evento.EventoResponse;
import com.serenity.api.serenity.dtos.evento.EventoRequest;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.repositories.EventoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoResponse cadastrar(EventoRequest eventoRequest) {
        var evento = new Evento();
        BeanUtils.copyProperties(eventoRequest,evento);
        return new EventoResponse(eventoRepository.save(evento));
    }

    public List<EventoResponse> listar() {
        return eventoRepository.findAll().stream()
                .map(evento -> new EventoResponse(evento))
                .collect(Collectors.toList());
    }

    public EventoResponse buscarPorId(Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return new EventoResponse(evento.get());
    }

    public void deletar(Integer id) {
        if (eventoRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        eventoRepository.deleteById(id);
    }

    public EventoResponse atualizar(Integer id, EventoRequest eventoRequest) {
        if (eventoRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var evento = new Evento();
        BeanUtils.copyProperties(eventoRequest, evento);

        return new EventoResponse(eventoRepository.save(evento));
    }
}
