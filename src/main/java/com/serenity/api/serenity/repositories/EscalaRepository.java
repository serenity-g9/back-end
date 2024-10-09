package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Escala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EscalaRepository extends JpaRepository<Escala, UUID> {
}
