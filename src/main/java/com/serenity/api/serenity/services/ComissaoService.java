package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.comissao.ComissaoRequest;
import com.serenity.api.serenity.dtos.comissao.ComissaoResponse;
import com.serenity.api.serenity.dtos.comissao.ComissaoUpdateRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Comissao;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import com.serenity.api.serenity.repositories.ComissaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ComissaoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ComissaoRepository comissaoRepository;

    public List<ComissaoResponse> listar() {
        return comissaoRepository.findAll().stream()
                .map(comissao -> new ComissaoResponse(comissao))
                .collect(Collectors.toList());
    }

    public ComissaoResponse cadastrar(ComissaoRequest comissaoRequest) {

        Optional<Agendamento> agendamentoOpt = agendamentoRepository.findById(comissaoRequest.idAgendamento());

        if (agendamentoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        var comissao = new Comissao();
        BeanUtils.copyProperties(comissaoRequest, comissao);

        comissao.setAgendamento(agendamentoOpt.get());
        comissao.setEmissao(LocalDateTime.now());
        comissao.setVencimento(LocalDate.from(comissao.getEmissao().plusDays(comissaoRequest.prazoDias())));
        comissao.setEfetuado(false);

        return new ComissaoResponse(comissaoRepository.save(comissao));
    }

    public ComissaoResponse buscarPorId(int id) {
        Optional<Comissao> comissaoOpt = comissaoRepository.findById(id);

        if (comissaoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return new ComissaoResponse(comissaoOpt.get());
    }

    public ComissaoResponse atualizar(int id, ComissaoUpdateRequest comissaoUpdateRequest) {
        if (!comissaoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var comissao = new Comissao();
        BeanUtils.copyProperties(comissaoUpdateRequest, comissao);
        comissao.setId(id);
        return new ComissaoResponse(comissaoRepository.save(comissao));
    }

    public void deletar (int id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        comissaoRepository.deleteById(id);
    }
}
