package com.serenity.api.serenity.services;

import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador cadastrar(Colaborador colaborador) {
        colaborador.setId(null);
        return colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> listar() {
        return colaboradorRepository.findAll();
    }

    public Colaborador buscarPorId(Integer id) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);

        if (colaborador.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return colaborador.get();
    }

    public void deletar(Integer id) {
        if (colaboradorRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        colaboradorRepository.deleteById(id);
    }

    public Colaborador atualizar(Integer id, Colaborador colaborador) {
        if (colaboradorRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return colaboradorRepository.save(colaborador);
    }
}
