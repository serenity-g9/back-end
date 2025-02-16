package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAllByEmailIn(Set<String> emai);
}
