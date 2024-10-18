package com.serenity.api.serenity.dtos.usuario;

public record SenhaPatchRequest(
//        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#@$%&])(?=.*[0-9])(?=.*[a-z]).{6,15}$")
        String senha) {
}
