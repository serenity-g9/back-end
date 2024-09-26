package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.pagamento.PagamentoRequest;
import com.serenity.api.serenity.dtos.pagamento.PagamentoResponse;
import com.serenity.api.serenity.dtos.pagamento.PagamentoUpdateRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Pagamento;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import com.serenity.api.serenity.repositories.PagamentoRepository;
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
public class PagamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final PagamentoRepository pagamentoRepository;

    public List<PagamentoResponse> listar() {
        return pagamentoRepository.findAll().stream()
                .map(pagamento -> new PagamentoResponse(pagamento))
                .collect(Collectors.toList());
    }

    public PagamentoResponse cadastrar(PagamentoRequest pagamentoRequest) {

        Optional<Agendamento> agendamentoOpt = agendamentoRepository.findById(pagamentoRequest.idAgendamento());

        if (agendamentoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        var pagamento = new Pagamento();
        BeanUtils.copyProperties(pagamentoRequest, pagamento);

        pagamento.setAgendamento(agendamentoOpt.get());
        pagamento.setEmissao(LocalDateTime.now());
        pagamento.setVencimento(LocalDate.from(pagamento.getEmissao().plusDays(pagamentoRequest.prazoDias())));
        pagamento.setEfetuado(false);

        return new PagamentoResponse(pagamentoRepository.save(pagamento));
    }

    public PagamentoResponse buscarPorId(int id) {
        Optional<Pagamento> pagamentoOpt = pagamentoRepository.findById(id);

        if (pagamentoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return new PagamentoResponse(pagamentoOpt.get());
    }

    public PagamentoResponse atualizar(int id, PagamentoUpdateRequest pagamentoUpdateRequest) {
        if (!pagamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var pagamento = new Pagamento();
        BeanUtils.copyProperties(pagamentoUpdateRequest, pagamento);
        pagamento.setId(id);
        return new PagamentoResponse(pagamentoRepository.save(pagamento));
    }

    public void deletar (int id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        pagamentoRepository.deleteById(id);
    }
}
