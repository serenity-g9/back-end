package com.serenity.api.serenity.services;

import com.serenity.api.serenity.dtos.dashboard.DashboardResponse;
import com.serenity.api.serenity.models.*;
import com.serenity.api.serenity.repositories.EventoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DashboardService {

    private final EventoRepository eventoRepository;

    public DashboardResponse buscarDados(UUID idUsuario, LocalDate dtInicio, LocalDate dtFim) {
        LocalDateTime inicio = dtInicio.atStartOfDay();
        LocalDateTime fim = dtFim.atStartOfDay();

        List<Evento> eventos = eventoRepository.findEventosByPeriodo(inicio, fim);

        Map<String, Integer> qtdPorStatusEvento = contarEventosPorStatus(eventos);
        Double balanco = somarOrcamentoEventos(eventos);
        Map<YearMonth, Long> qtdEventosPorAnoMes = buscarQtdEventosPorAnoMes(eventos);

        return new DashboardResponse(
                balanco,
                calcularMediaPercentualDepois(eventos),
                contarCodigosAntesDepois(eventos),
                qtdEventosPorAnoMes,
                qtdPorStatusEvento,
                dtInicio,
                dtFim
        );
    }

    private Map<String, Integer> contarEventosPorStatus(List<Evento> eventos) {
        LocalDateTime agora = LocalDateTime.now();

        Map<String, Integer> resultado = new HashMap<>();

        for (Evento evento : eventos) {
            String status;
            if (agora.isAfter(evento.getFim())) {
                status = "FINALIZADO";
            } else if (agora.isBefore(evento.getInicio())) {
                status = "EM_BREVE";
            } else {
                status = "OCORRENDO";
            }

            resultado.merge(status, 1, Integer::sum);
        }

        resultado.put("PENDENTE", 0);

        return resultado;
    }

    private Double somarOrcamentoEventos(List<Evento> eventos) {
        return eventos.stream()
                .mapToDouble(Evento::getOrcamento)
                .sum();
    }

    private Map<YearMonth, Long> buscarQtdEventosPorAnoMes(List<Evento> eventos) {
       return eventos.stream()
            .collect(Collectors.groupingBy(
                    e -> YearMonth.from(e.getCreatedAt()),
                    Collectors.counting()
            ));
    }

    private Map<String, Long> contarCodigosAntesDepois(List<Evento> eventos) {
        long countAntes = 0;
        long countDepois = 0;

        for (Evento evento : eventos) {
            for (Demanda demanda : evento.getDemandas()) {
                for (Escala escala : demanda.getEscalas()) {
                    for (Agendamento agendamento : escala.getAgendamentos()) {
                        Codigo codEntrada = agendamento.getCodEntrada();
                        if (codEntrada != null && codEntrada.getHorarioUtilizado() != null) {
                            LocalDateTime utilizado = codEntrada.getHorarioUtilizado();
                            LocalDateTime entrada = agendamento.getHorarioEntrada();

                            if (utilizado.isBefore(entrada)) {
                                countAntes++;
                            } else {
                                countDepois++;
                            }
                        }
                    }
                }
            }
        }

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("pontual", countAntes);
        resultado.put("emAtraso", countDepois);
        return resultado;
    }

    public Double calcularMediaPercentualDepois(List<Evento> eventos) {
        List<Double> percentuaisDepois = new ArrayList<>();

        for (Evento evento : eventos) {
            long countAntes = 0;
            long countDepois = 0;

            for (Demanda demanda : evento.getDemandas()) {
                for (Escala escala : demanda.getEscalas()) {
                    for (Agendamento agendamento : escala.getAgendamentos()) {
                        Codigo cod = agendamento.getCodEntrada();
                        if (cod != null && cod.getHorarioUtilizado() != null) {
                            LocalDateTime utilizado = cod.getHorarioUtilizado();
                            LocalDateTime entrada = agendamento.getHorarioEntrada();

                            if (utilizado.isBefore(entrada)) {
                                countAntes++;
                            } else {
                                countDepois++;
                            }
                        }
                    }
                }
            }

            long total = countAntes + countDepois;
            if (total > 0) {
                double percentualDepois = (double) countDepois / total;
                percentuaisDepois.add(percentualDepois);
            }
        }

        return percentuaisDepois.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}
