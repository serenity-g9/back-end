package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.StatusAgendamento;
import com.serenity.api.serenity.listeners.AgendamentoListener;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EntityListeners(AgendamentoListener.class)
public class Agendamento extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private LocalDateTime horarioInvitacaoAceito;

    @ManyToOne
    @JoinColumn(name = "escala_id", nullable = false)
    private Escala escala;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Codigo codEntrada;

    @OneToOne
    private Codigo codSaida;

    public String getStatus() {
        if (escala.getDemanda().getFim().isBefore(LocalDateTime.now())) {
            return StatusAgendamento.getValor(4);
        } else if (usuario == null) {
            return StatusAgendamento.getValor(0);
        } else if (horarioInvitacaoAceito == null) {
            return StatusAgendamento.getValor(1);
        } else if (codEntrada.getHorarioUtilizado() == null) {
            return StatusAgendamento.getValor(2);
        } else {
            return StatusAgendamento.getValor(3);
        }
    }
}
