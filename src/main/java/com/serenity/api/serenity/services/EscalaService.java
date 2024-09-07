package com.serenity.api.serenity.services;

import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import com.serenity.api.serenity.repositories.EscalaRepository;
import com.serenity.api.serenity.repositories.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EscalaService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private EscalaRepository escalaRepository;

    public List<Escala> listar() {
        return escalaRepository.findAll();
    }

    public Escala cadastrar(Escala escala) {
        escala.setId(null);

        if (!colaboradorRepository.existsById(escala.getIdColaborador()) || !eventoRepository.existsById(escala.getIdEvento())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        escala.setDataHorario(LocalDateTime.now());

        return escalaRepository.save(escala);
    }

    public Escala buscarPorId(int id) {
        Optional<Escala> escala = escalaRepository.findById(id);

        if (escala.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return escala.get();
    }

    public Escala atualizar(int id, Escala escalaAtualizado) {
        if (!escalaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        escalaAtualizado.setId(id);
        return escalaRepository.save(escalaAtualizado);
    }

    public void deletar (int id) {
        if (!escalaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        escalaRepository.deleteById(id);
    }

    public List<Escala> buscarEscalasPorIdUsuario(int idUsuario){
        return escalaRepository.findAllByIdColaborador(idUsuario);
    }

    public List<Escala> buscarEscalasPorIdEvento(int idEvento){
        return escalaRepository.findAllByIdEvento(idEvento);
    }
}
