package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.repositories.DemandaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DemandaService {
    private final DemandaRepository demandaRepository;
    private final EventoService eventoService;

    public Demanda cadastrar(Demanda demanda) {

        demanda.getEscalas().forEach(escala -> escala.setDemanda(demanda));

        return demandaRepository.save(demanda);
    }

    public List<Demanda> listar() {
        return demandaRepository.findAll();
    }

    public Demanda buscarPorId(UUID id) {
        return demandaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Demanda"));
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        demandaRepository.deleteById(id);
    }

    public Demanda atualizar(UUID id, Demanda Demanda) {
        buscarPorId(id);
        Demanda.setId(id);

        return demandaRepository.save(Demanda);
    }
}
