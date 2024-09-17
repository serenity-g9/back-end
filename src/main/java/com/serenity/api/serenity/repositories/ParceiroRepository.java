package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Parceiro;
import com.serenity.api.serenity.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParceiroRepository extends JpaRepository<Parceiro, Integer> {
    Boolean existsByUsuario(Usuario usuario);
}
