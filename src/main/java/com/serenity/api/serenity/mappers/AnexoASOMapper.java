package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.anexosASO.AnexoASORequest;
import com.serenity.api.serenity.dtos.anexosASO.AnexoASOUpdateRequest;
import com.serenity.api.serenity.dtos.comissao.ComissaoUpdateRequest;
import com.serenity.api.serenity.models.AnexoASO;
import com.serenity.api.serenity.models.Comissao;
import com.serenity.api.serenity.services.AnexoASOService;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public abstract class AnexoASOMapper {

    @Autowired
    private AnexoASOService anexoASOService;

    @Mapping(target = "AnexoASO", ignore = true)
    public abstract AnexoASO toAnexoASO(AnexoASORequest AnexoRequest);


    @Mapping(target = "agendamento", ignore = true)
    public abstract AnexoASO UpdateToAnexoASO(AnexoASOUpdateRequest anexoASOUpdateRequest, @MappingTarget AnexoASO anexoASO);

    protected AnexoASO mapIdAnexoASOToAnexoASO(UUID idAnexoASO) {
        return anexoASOService.buscarPorId(idAnexoASO);
    }
}
