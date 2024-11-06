package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.evento.EventoExportResponse;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.repositories.EventoRepository;
import com.serenity.api.serenity.utils.CSVUtil;
import com.serenity.api.serenity.utils.SearchUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final AnexoService anexoService;

    public Evento cadastrar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public List<Evento> buscarPorNome(String nome) {
        if (nome.isBlank()) return eventoRepository.findAllByOrderByNomeAsc();

        Evento[] eventos = eventoRepository.findAllByOrderByNomeAsc().toArray(Evento[]::new);

        int indice = SearchUtil.binarySearch(eventos, nome);
        if (indice < 0) throw new NaoEncontradoException("evento");

        return List.of(eventos[indice]);
    }

    public Evento buscarPorId(UUID id) {
        return eventoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("evento"));
    }

    public void deletar(UUID id) {
        Evento evento = buscarPorId(id);
        if (evento.getImagem() != null) anexoService.deletarAnexo(evento.getImagem().getNome());
        eventoRepository.deleteById(id);
    }

    public Evento atualizar(UUID id, Evento evento) {
        buscarPorId(id);
        evento.setId(id);

        return eventoRepository.save(evento);
    }

    public String exportar(LocalDateTime inicio, LocalDateTime fim, Integer limite) {
        Pageable pageable = PageRequest.of(0, limite);
        List<Evento> paraExportar = eventoRepository.findByInicioBetween(inicio, fim, pageable);

        List<EventoExportResponse> eventoResponses = paraExportar.stream()
                .map(EventoExportResponse::new)
                .toList();

        return CSVUtil.exportar(eventoResponses);
    }
}
