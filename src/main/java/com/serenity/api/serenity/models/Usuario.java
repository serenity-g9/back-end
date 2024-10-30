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
public class Usuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;
    private String senha;
    private Integer tipoUsuario;

    @Embedded
    Contato contato;

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
