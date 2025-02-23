package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.interfaces.BaseRepository;
import com.serenity.api.serenity.models.Evento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventoRepository extends BaseRepository<Evento, UUID> {

    List<Evento> findByInicioBetween(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);

    @Query("SELECT e FROM Evento e WHERE e.deletedAt IS NULL ORDER BY e.nome ASC")
    List<Evento> findAllByOrderByNomeAsc();

    @Query("select count(*) from evento where  responsavel_id = idUsuario and now() > fim ")
    Integer findByNumeroDeEventosFinalizados(UUID idUsuario);

    @Query("select count(*) from evento where  responsavel_id = idUsuario and now() < inicio ")
    Integer findByNumeroDeEventosEmBreve(UUID idUsuario);

    @Query("select count(*) from evento where  responsavel_id = idUsuario and now() between inicio and fim ")
    Integer findByNumeroDeEvetosOcorrendo(UUID idUsuario);

    @Query("select count(*) from evento where  responsavel_id = idUsuario and between inicio and fim ")
    Integer findByNumeroDeEventosQuantidadeMes(UUID idUsuario, LocalDate inicio , LocalDate fim);


}