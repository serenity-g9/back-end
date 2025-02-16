package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.interfaces.BaseRepository;
import com.serenity.api.serenity.models.Codigo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CodigoRepository extends BaseRepository<Codigo, UUID> {
    @Query("SELECT c FROM Codigo c WHERE c.digito = :digito AND c.horarioUtilizado IS NULL")
    List<Codigo> buscarCodigoPorSequencia(String digito);
}
