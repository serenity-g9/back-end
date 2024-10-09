package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
}
