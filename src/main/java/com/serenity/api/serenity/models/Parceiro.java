package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.PermissaoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Parceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private PermissaoUsuario permissao;
}
