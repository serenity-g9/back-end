package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.evento.EventoRequest;
import com.serenity.api.serenity.models.Evento;
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
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento cadastrar(EventoRequest eventoRequest) {
        var evento = new Evento();
        BeanUtils.copyProperties(eventoRequest,evento);
        return eventoRepository.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return evento.get();
    }

    public void deletar(Integer id) {
        if (eventoRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        eventoRepository.deleteById(id);
    }

    public Evento atualizar(Integer id, EventoRequest eventoRequest) {
        if (eventoRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var evento = new Evento();
        BeanUtils.copyProperties(eventoRequest,evento);
        return eventoRepository.save(evento);
    }
}
