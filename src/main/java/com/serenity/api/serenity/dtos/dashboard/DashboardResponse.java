package com.serenity.api.serenity.dtos.dashboard;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

public record DashboardResponse(
        Double balanco,
        Double atrasosPorEventoPercentual,
        Map<String, Long> qtdAtrasosPorEventos,
        Map<YearMonth, Long> qtdEventosPorAnoMes,
        Map<String, Integer> qtdPorStatusEvento,
        LocalDate inicio,
        LocalDate fim
) {
    public DashboardResponse(Double balanco, Double atrasosPorEventoPercentual, Map<String, Long> qtdAtrasosPorEventos, Map<YearMonth, Long> qtdEventosPorAnoMes, Map<String, Integer> qtdPorStatusEvento, LocalDate inicio, LocalDate fim) {
        this.balanco = balanco;
        this.atrasosPorEventoPercentual = atrasosPorEventoPercentual;
        this.qtdAtrasosPorEventos = qtdAtrasosPorEventos;
        this.qtdEventosPorAnoMes = qtdEventosPorAnoMes;
        this.qtdPorStatusEvento = qtdPorStatusEvento;
        this.inicio = inicio;
        this.fim = fim;
    }
}
