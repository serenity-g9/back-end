package com.serenity.api.serenity.services;

import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioService usuarioService;

    public Colaborador cadastrar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> listar() {
        return colaboradorRepository.findAll();
    }

    public Colaborador buscarPorId(Integer id) {
        return colaboradorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        colaboradorRepository.deleteById(id);
    }

    public Colaborador atualizar(Integer id, Colaborador colaborador) {
        buscarPorId(id);
        colaborador.setId(id);

        return colaboradorRepository.save(colaborador);
    }

}
