package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.parceiro.ParceiroRequest;
import com.serenity.api.serenity.dtos.parceiro.ParceiroResponse;
import com.serenity.api.serenity.dtos.parceiro.ParceiroUpdateRequest;
import com.serenity.api.serenity.models.Pagamento;
import com.serenity.api.serenity.models.Parceiro;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.ParceiroRepository;
import com.serenity.api.serenity.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
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
public class ParceiroService {

    @Autowired
    private ParceiroRepository parceiroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Parceiro cadastrar(ParceiroRequest parceiroRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(parceiroRequest.idUsuario());

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        var parceiro = new Parceiro();
        BeanUtils.copyProperties(parceiroRequest, parceiro);
        parceiro.setUsuario(usuarioOpt.get());

        return parceiroRepository.save(parceiro);
    }

    public List<ParceiroResponse> listar() {
        return parceiroRepository.findAll().stream()
                .map(parceiro -> new ParceiroResponse(parceiro))
                .collect(Collectors.toList());
    }

    public ParceiroResponse buscarPorId(Integer id) {
        Optional<Parceiro> parceiro = parceiroRepository.findById(id);

        if (parceiro.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return new ParceiroResponse(parceiro.get());
    }

    public void deletar(Integer id) {
        if (parceiroRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        parceiroRepository.deleteById(id);
    }

    public ParceiroResponse atualizar(Integer id, ParceiroUpdateRequest parceiroUpdateRequest) {
        if (parceiroRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var parceiro = new Parceiro();
        BeanUtils.copyProperties(parceiroUpdateRequest, parceiro);
        parceiro.setId(id);

        return new ParceiroResponse(parceiroRepository.save(parceiro));
    }
}
