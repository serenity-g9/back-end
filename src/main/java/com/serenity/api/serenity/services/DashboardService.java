package com.serenity.api.serenity.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor

public class DashboardService {
    private final EventoService eventoService;


}
