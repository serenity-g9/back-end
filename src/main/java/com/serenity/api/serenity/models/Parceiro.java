package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.PermissaoUsuario;
import com.serenity.api.serenity.models.embeddable.Contato;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parceiro  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Usuario usuario;

    @Embedded
    private Contato contato;

    @Enumerated(EnumType.STRING)
    private PermissaoUsuario permissao;

}
