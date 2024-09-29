package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.repositories.EscalaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Escala buscarPorId(Integer id) {
        return escalaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("escala"));
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        escalaRepository.deleteById(id);
    }

    public Escala atualizar(Integer id, Escala escala) {
        buscarPorId(id);
        escala.setId(id);

        return escalaRepository.save(escala);
    }
}
