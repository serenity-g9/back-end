package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.colaborador.ColaboradorRequest;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorUpdateRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
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
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador cadastrar(ColaboradorRequest colaboradorRequest) {
        var colaborador = new Colaborador();
        BeanUtils.copyProperties(colaboradorRequest, colaborador);
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

    public Colaborador atualizar(Integer id, ColaboradorUpdateRequest colaboradorUpdateRequest) {
        if (colaboradorRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var colaborador = new Colaborador();
        BeanUtils.copyProperties(colaboradorUpdateRequest, colaborador);
        colaborador.setId(id);
        return colaboradorRepository.save(colaborador);
    }
}
