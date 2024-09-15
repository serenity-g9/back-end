package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
