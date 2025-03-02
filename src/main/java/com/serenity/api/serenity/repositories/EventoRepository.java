package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.interfaces.BaseRepository;
import com.serenity.api.serenity.models.Evento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventoRepository extends BaseRepository<Evento, UUID> {

    List<Evento> findByInicioBetween(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);

    @Query("SELECT e FROM Evento e WHERE e.deletedAt IS NULL ORDER BY e.nome ASC")
    List<Evento> findAllByOrderByNomeAsc();

//    @Query("SELECT COUNT(e) FROM Evento e WHERE e.responsavel.id = :idUsuario AND CURRENT_TIMESTAMP > e.fim")
//    Integer findByNumeroDeEventosFinalizados(@Param("idUsuario") UUID idUsuario);
//
//    @Query("SELECT COUNT(e) FROM Evento e WHERE e.responsavel.id = :idUsuario AND CURRENT_TIMESTAMP < e.inicio")
//    Integer findByNumeroDeEventosEmBreve(@Param("idUsuario") UUID idUsuario);
//
//    @Query("SELECT COUNT(e) FROM Evento e WHERE e.responsavel.id = :idUsuario AND CURRENT_TIMESTAMP BETWEEN e.inicio AND e.fim")
//    Integer findByNumeroDeEventosOcorrendo(@Param("idUsuario") UUID idUsuario);
//
//    @Query("SELECT YEAR(e.inicio), MONTH(e.inicio), COUNT(e) FROM Evento e WHERE e.responsavel.id = :idUsuario AND e.inicio BETWEEN :dataInicio AND :dataFim GROUP BY YEAR(e.inicio), MONTH(e.inicio)")
//    List<Integer> findByEventosPorMes(@Param("idUsuario") UUID idUsuario, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);



}