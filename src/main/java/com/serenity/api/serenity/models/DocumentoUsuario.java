package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.TipoAnexo;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "anexo_id")
    private Anexo anexo;

    @Enumerated(EnumType.STRING)
    private TipoAnexo tipoAnexo;
}
