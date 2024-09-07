package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    List<Registro> findAllByIdEscalaIn(List<Integer> ids);
}
