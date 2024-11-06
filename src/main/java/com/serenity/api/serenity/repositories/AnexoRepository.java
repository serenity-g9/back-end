package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnexoRepository extends JpaRepository<Anexo, UUID> {
}
