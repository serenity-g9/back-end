package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Escala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
    @Query("""
        SELECT a
        FROM Agendamento a
        LEFT JOIN FETCH a.codEntrada ce
        WHERE (:status = 0 AND a.usuario IS NULL)
           OR (:status = 1 AND a.usuario IS NOT NULL AND a.horarioInvitacaoAceito IS NULL)
           OR (:status = 2 AND a.horarioInvitacaoAceito IS NOT NULL AND ce.horarioUtilizado IS NULL)
           OR (:status = 3 AND ce.horarioUtilizado IS NOT NULL)
        """)
    List<Agendamento> buscarPorStatus(Integer status);

    List<Agendamento> findAllByEscalaId(UUID id);
}
