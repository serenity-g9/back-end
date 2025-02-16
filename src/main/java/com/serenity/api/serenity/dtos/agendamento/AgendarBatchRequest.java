package com.serenity.api.serenity.dtos.agendamento;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AgendarBatchRequest(
        @NotEmpty
        @NotNull
        Set<@Email String> emails
) {
}
