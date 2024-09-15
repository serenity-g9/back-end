package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.escala.EscalaRequest;
import com.serenity.api.serenity.dtos.escala.EscalaUpdateRequest;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.repositories.EscalaRepository;
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
public class EscalaService {

    @Autowired
    private EscalaRepository escalaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Escala cadastrar(EscalaRequest escalaRequest) {
        Optional<Evento> eventoOpt = eventoRepository.findById(escalaRequest.idEvento());

        if (eventoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        Escala escala = new Escala();
        BeanUtils.copyProperties(escalaRequest, escala);
        escala.setEvento(eventoOpt.get());

        return escalaRepository.save(escala);
    }

    public List<Escala> listar() {
        return escalaRepository.findAll();
    }

    public Escala buscarPorId(Integer id) {
        Optional<Escala> escalaOpt = escalaRepository.findById(id);

        if (escalaOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return escalaOpt.get();
    }

    public void deletar(Integer id) {
        if (escalaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        escalaRepository.deleteById(id);
    }

    public Escala atualizar(Integer id, EscalaUpdateRequest escalaUpdateRequest) {
        if (escalaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        Escala escala = new Escala();
        BeanUtils.copyProperties(escalaUpdateRequest, escala);
        escala.setId(id);
        return escalaRepository.save(escala);
    }
}
