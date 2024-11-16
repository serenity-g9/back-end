package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EscalaRepository extends JpaRepository<Escala, UUID> {
    @Query("""
        SELECT a
        FROM Agendamento a
        LEFT JOIN a.escala e
        WHERE (a.usuario IS NULL)
          AND (e.id = :idEscala)
    """)
    Page<Agendamento> buscarAgendamentosDisponiveisPorEscala(UUID idEscala, Pageable pageable);
}
