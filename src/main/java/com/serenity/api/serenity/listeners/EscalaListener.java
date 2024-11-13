package com.serenity.api.serenity.listeners;

import com.serenity.api.serenity.events.EscalaEvent;
import com.serenity.api.serenity.models.Escala;
import jakarta.persistence.PostPersist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class EscalaListener {

    private final ApplicationEventPublisher eventPublisher;

    @PostPersist
    public void postPersist(Escala escala) {
        eventPublisher.publishEvent(new EscalaEvent(escala));
    }
}
