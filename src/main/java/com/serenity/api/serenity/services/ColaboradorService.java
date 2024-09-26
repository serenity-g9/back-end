package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.colaborador.ColaboradorRequest;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorResponse;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorUpdateRequest;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.ColaboradorRepository;
import com.serenity.api.serenity.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioRepository usuarioRepository;

    public ColaboradorResponse cadastrar(ColaboradorRequest colaboradorRequest) {
        Optional<Usuario> usuario = usuarioRepository.findById(colaboradorRequest.idUsuario());

        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        var colaborador = new Colaborador();
        BeanUtils.copyProperties(colaboradorRequest, colaborador);
        colaborador.setUsuario(usuario.get());

        return new ColaboradorResponse(colaboradorRepository.save(colaborador));
    }

    public List<ColaboradorResponse> listar() {
        return colaboradorRepository.findAll().stream()
                .map(colaborador -> new ColaboradorResponse(colaborador))
                .collect(Collectors.toList());
    }

    public ColaboradorResponse buscarPorId(Integer id) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);

        if (colaborador.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return new ColaboradorResponse(colaborador.get());
    }

    public void deletar(Integer id) {
        if (colaboradorRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        colaboradorRepository.deleteById(id);
    }

    public ColaboradorResponse atualizar(Integer id, ColaboradorUpdateRequest colaboradorUpdateRequest) {
        if (colaboradorRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var colaborador = new Colaborador();
        BeanUtils.copyProperties(colaboradorUpdateRequest, colaborador);
        colaborador.setId(id);
        return new ColaboradorResponse(colaboradorRepository.save(colaborador));
    }
}
