package com.serenity.api.serenity.helpers;

import com.serenity.api.serenity.events.GerarCodigoEvent;
import com.serenity.api.serenity.models.Agendamento;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendamentoHelper {
    private final ApplicationEventPublisher eventPublisher;

    public void handlePreUpdate(Agendamento agendamento) {
        eventPublisher.publishEvent(new GerarCodigoEvent(agendamento));
    }
}
