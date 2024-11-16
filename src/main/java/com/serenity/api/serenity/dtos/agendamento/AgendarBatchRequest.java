package com.serenity.api.serenity.dtos.agendamento;

import java.util.Set;
import java.util.UUID;

public record AgendarBatchRequest(
        Set<UUID> usuariosId
) {
}
