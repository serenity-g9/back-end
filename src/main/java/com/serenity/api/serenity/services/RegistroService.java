package com.serenity.api.serenity.services;

import com.serenity.api.serenity.models.Registro;
import com.serenity.api.serenity.repositories.RegistroRepository;
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
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public Registro cadastrar(Registro registro) {
        registro.setId(null);
        registro.setDataHorario(LocalDateTime.now());
        return registroRepository.save(registro);
    }

    public List<Registro> listar() {
        return registroRepository.findAll();
    }

    public Optional<Registro> buscarPorId(Integer id) {
        Optional<Registro> registro = registroRepository.findById(id);

        if (registro.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return registro;
    }

    public void deletar(Integer id) {
        if (registroRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        registroRepository.deleteById(id);
    }

    public Registro atualizar(Integer id, Registro registro) {
        if (registroRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return registroRepository.save(registro);
    }
}