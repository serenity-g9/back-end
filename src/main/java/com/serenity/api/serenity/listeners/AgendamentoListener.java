package com.serenity.api.serenity.listeners;

import com.serenity.api.serenity.helpers.AgendamentoHelper;
import com.serenity.api.serenity.models.Agendamento;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AgendamentoListener {

    private final AgendamentoHelper agendamentoHelper;

    @PreUpdate
    public void preUpdate(Agendamento agendamento) {
        agendamentoHelper.handlePreUpdate(agendamento);
    }
}