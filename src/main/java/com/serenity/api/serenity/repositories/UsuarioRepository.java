package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.ativo = true")
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByEmailIn(Set<String> emai);

}
