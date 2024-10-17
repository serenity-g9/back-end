package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Evento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EscalaRepository extends JpaRepository<Escala, UUID> {
}
