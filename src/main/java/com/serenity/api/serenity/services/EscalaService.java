package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.repositories.EscalaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EscalaService {


    private final EscalaRepository escalaRepository;
    private final EventoService eventoService;

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
}
