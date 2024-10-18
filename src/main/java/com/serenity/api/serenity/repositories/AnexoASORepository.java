package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.AnexoASO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnexoASORepository extends JpaRepository<AnexoASO, UUID> {
}
