package com.serenity.api.serenity.models;


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
public class Usuario extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;
    private String senha;
    private Integer tipoUsuario;
    private Boolean ativo;

    @Embedded
    Contato contato;

    @OneToOne(cascade = CascadeType.ALL)
    private Anexo imagem;

    public Usuario(UUID id, String email, String senha, Integer tipoUsuario) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s\n",
                id,
                email,
                senha,
                tipoUsuario
        );
    }

}
