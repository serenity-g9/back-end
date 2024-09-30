package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.FuncaoInteresse;
import com.serenity.api.serenity.models.embeddable.Contato;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colaborador implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<FuncaoInteresse> funcoesInteresse;

    private String cidadeResidencia;
    private String estado;
    private String apresentacao;
    private String experiencia;
    private String linkSocial;
    private Boolean sociaisAbertas;

    @Embedded
    private Contato contato;

    @OneToOne
    private Usuario usuario;
}
