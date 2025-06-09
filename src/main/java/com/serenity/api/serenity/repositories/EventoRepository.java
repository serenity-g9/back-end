package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.interfaces.BaseRepository;
import com.serenity.api.serenity.models.Evento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventoRepository extends BaseRepository<Evento, UUID> {

    List<Evento> findByInicioBetween(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);

    @Query("SELECT e FROM Evento e WHERE e.deletedAt IS NULL ORDER BY e.nome ASC")
    List<Evento> findAllByOrderByNomeAsc();

    @Query("""
    SELECT e FROM Evento e
    WHERE e.responsavel.id = :idUsuario
      AND (:inicio IS NULL OR e.inicio >= :inicio)
      AND (:fim IS NULL OR e.fim <= :fim)
      AND e.deletedAt IS NULL
    """)
    List<Evento> findEventosByResponsavelAndPeriodo(UUID idUsuario, LocalDateTime inicio, LocalDateTime fim);
}