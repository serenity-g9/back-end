package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.models.Comissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;
import java.util.UUID;

public interface ComissaoRepository extends JpaRepository<Comissao, UUID> {
}
