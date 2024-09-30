package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.repositories.EventoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public Evento cadastrar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(UUID id) {
        return eventoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("evento"));
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        eventoRepository.deleteById(id);
    }

    public Evento atualizar(UUID id, Evento evento) {
        buscarPorId(id);
        evento.setId(id);

        return eventoRepository.save(evento);
    }
}
