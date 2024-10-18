package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.anexosASO.AnexoASORequest;
import com.serenity.api.serenity.dtos.anexosASO.AnexoASOUpdateRequest;
import com.serenity.api.serenity.dtos.comissao.ComissaoUpdateRequest;
import com.serenity.api.serenity.models.AnexoASO;
import com.serenity.api.serenity.models.Comissao;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.services.AnexoASOService;
import com.serenity.api.serenity.services.UsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper
public abstract class AnexoASOMapper {

    @Autowired
    private UsuarioService usuarioService;

    @Mapping(target = "usuario", source = "idUsuario")
    public abstract AnexoASO toAnexoASO(AnexoASORequest AnexoRequest);

    @Mapping(target = "usuario", ignore = true)
    public abstract AnexoASO UpdateToAnexoASO(AnexoASOUpdateRequest anexoASOUpdateRequest, @MappingTarget AnexoASO anexoASO);

    protected Usuario mapIdUsuarioToUsuario(UUID idUsuario) {
        return usuarioService.buscarPorId(idUsuario);
    }
}
