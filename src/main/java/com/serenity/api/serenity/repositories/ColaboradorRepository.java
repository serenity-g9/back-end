package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColaboradorRepository extends JpaRepository<Colaborador, UUID> {
    Boolean existsByUsuario(Usuario usuario);
}
