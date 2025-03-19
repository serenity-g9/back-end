package com.serenity.api.serenity.repositories;

import com.serenity.api.serenity.enums.TipoAnexo;
import com.serenity.api.serenity.models.DocumentoUsuario;
import com.serenity.api.serenity.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DocumentoUsuarioRepository extends JpaRepository<DocumentoUsuario, UUID> {
    List<DocumentoUsuario> findByUsuario(Usuario usuario);
    boolean existsDocumentoUsuarioByTipoAnexo(TipoAnexo tipoAnexo);
}
