package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}
