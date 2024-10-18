package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Pagamento;
import com.serenity.api.serenity.repositories.PagamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public List<Pagamento> listar() {
        return pagamentoRepository.findAll();
    }

    public Pagamento cadastrar(Pagamento pagamento) {
        pagamento.setEfetuado(false);

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento buscarPorId (UUID id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("pagamento"));
    }

    public Pagamento atualizar(UUID id, Pagamento pagamento) {
        buscarPorId(id);
        pagamento.setId(id);

        return pagamentoRepository.save(pagamento);
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        pagamentoRepository.deleteById(id);
    }
}
