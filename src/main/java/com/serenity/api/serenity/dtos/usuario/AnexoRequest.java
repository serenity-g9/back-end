package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.models.Anexo;
import org.springframework.web.bind.annotation.RequestBody;

public record  AnexoRequest(
        String nomeArquivo,
        String tipoAnexo,// Aqui você recebe o enum como string
        Anexo arquivo,
        Integer quantidade
) {

}
