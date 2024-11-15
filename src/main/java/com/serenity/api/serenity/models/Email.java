package com.serenity.api.serenity.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email {
    @jakarta.validation.constraints.Email
    private String remetente;
    @jakarta.validation.constraints.Email
    private String destinatario;
    @NotBlank
    @Size(min = 3,max = 60)
    private String titulo;
    @NotBlank
    @Size(min = 3,max = 600)
    private String conteudo;
}
