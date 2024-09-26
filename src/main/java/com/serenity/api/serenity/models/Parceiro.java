package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.PermissaoUsuario;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private PermissaoUsuario permissao;
}
