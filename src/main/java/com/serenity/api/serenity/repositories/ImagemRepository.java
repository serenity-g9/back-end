package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImagemRepository extends JpaRepository<Imagem, UUID> {
}
