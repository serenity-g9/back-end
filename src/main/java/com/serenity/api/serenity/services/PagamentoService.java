package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Pagamento;
import com.serenity.api.serenity.repositories.PagamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Pagamento buscarPorId(int id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("pagamento"));
    }

    public Pagamento atualizar(int id, Pagamento pagamento) {
        buscarPorId(id);
        pagamento.setId(id);

        return pagamentoRepository.save(pagamento);
    }

    public void deletar(int id) {
        buscarPorId(id);
        pagamentoRepository.deleteById(id);
    }
}
