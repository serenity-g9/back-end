package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
