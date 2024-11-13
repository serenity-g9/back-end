package com.serenity.api.serenity.listeners;

import com.serenity.api.serenity.events.GerarCodigoEvent;
import com.serenity.api.serenity.models.Agendamento;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class AgendamentoListener {

    private final ApplicationEventPublisher eventPublisher;

    @PreUpdate
    public void preUpdate(Agendamento agendamento) {
        eventPublisher.publishEvent(new GerarCodigoEvent(agendamento));
    }

}
