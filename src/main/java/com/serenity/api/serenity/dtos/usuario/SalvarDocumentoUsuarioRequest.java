package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.enums.TipoAnexo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Builder
public class SalvarDocumentoUsuarioRequest {

    @Null
    private MultipartFile contentFile;

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private UUID idUsuario;

    @NotNull(message = "O tipo do anexo não pode ser nulo")
    private TipoAnexo tipoAnexo;
}
