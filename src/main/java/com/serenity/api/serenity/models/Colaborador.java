package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.FuncaoInteresse;
import com.serenity.api.serenity.models.embeddable.Contato;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
