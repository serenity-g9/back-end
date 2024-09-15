package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    Boolean existsByUsuario(Usuario usuario);
}
