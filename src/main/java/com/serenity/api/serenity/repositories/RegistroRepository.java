package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    Boolean existsByTipoRegistro(int tipoRegistro);
}
