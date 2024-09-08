package com.serenity.api.serenity.services;

import com.serenity.api.serenity.models.Parceiro;
import com.serenity.api.serenity.repositories.ParceiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParceiroService {

    @Autowired
    private ParceiroRepository parceiroRepository;

    @Autowired
    private RegistroService registroService;

    public Parceiro cadastrar(Parceiro parceiro) {
        parceiro.setId(null);
        return parceiroRepository.save(parceiro);
    }

    public List<Parceiro> listar() {
        return parceiroRepository.findAll();
    }

    public Optional<Parceiro> buscarPorId(Integer id) {
        Optional<Parceiro> parceiro = parceiroRepository.findById(id);

        if (parceiro.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return parceiro;
    }

    public void deletar(Integer id) {
        if (parceiroRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        parceiroRepository.deleteById(id);
    }

    public Parceiro atualizar(Integer id, Parceiro parceiro) {
        if (parceiroRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return parceiroRepository.save(parceiro);
    }
}
