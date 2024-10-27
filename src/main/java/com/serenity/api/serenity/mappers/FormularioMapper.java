package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.formulario.FormularioRequest;
import com.serenity.api.serenity.dtos.formulario.FormularioUpdateRequest;
import com.serenity.api.serenity.models.Formulario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class FormularioMapper {

    public abstract Formulario toFormulario(FormularioRequest formularioRequest);
    public abstract Formulario toFormulario(FormularioUpdateRequest formularioRequest, @MappingTarget Formulario formulario);
}
