package com.serenity.api.serenity.dtos.evento;

import com.serenity.api.serenity.dtos.formulario.FormularioResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.enums.TipoContrato;
import com.serenity.api.serenity.models.Anexo;
import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.models.embeddable.Endereco;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EventoDemandasResponse(
        UUID id,
        String nome,
        Double orcamento,
        LocalDateTime inicio,
        LocalDateTime fim,
        Endereco endereco,
        Anexo imagem,
        FormularioResponse formulario,
        UsuarioResponse responsavel,
        String status,
        List<DemandaResponse> demandas,
        List<EscalaResponse> escalas
) {
    public EventoDemandasResponse(Evento evento) {
        this(
                evento.getId(),
                evento.getNome(),
                evento.getOrcamento(),
                evento.getInicio(),
                evento.getFim(),
                evento.getEndereco(),
                evento.getImagem(),
                evento.getFormulario() == null ? null : new FormularioResponse(evento.getFormulario()),
                evento.getResponsavel() == null ? null : new UsuarioResponse(evento.getResponsavel()),
                evento.getInicio().isAfter(LocalDateTime.now())
                        ? "Não iniciado"
                        : evento.getFim().isAfter(LocalDateTime.now())
                        ? "Em andamento"
                        : "Finalizado",
                evento.getDemandas() == null ? null : evento.getDemandas().stream().map(DemandaResponse::new).toList(),
                evento.getDemandas() == null ? null : evento.getDemandas().stream()
                        .flatMap(demanda -> demanda.getEscalas().stream())
                        .map(EscalaResponse::new)
                        .toList()
        );
    }
}


record DemandaResponse(
        UUID id,
        String nome,
        Double custoTotal,
        LocalDateTime inicio,
        LocalDateTime fim,
        String tipoContrato,
        UsuarioResponse responsavel,
        List<EscalaResponse> escalas
) {
    public DemandaResponse(Demanda demanda) {
        this(
                demanda.getId(),
                demanda.getNome(),
                demanda.getCustoTotal(),
                demanda.getInicio(),
                demanda.getFim(),
                TipoContrato.getValor(demanda.getTipoContrato()),
                demanda.getResponsavel() != null ? new UsuarioResponse(demanda.getResponsavel()) : null,
                demanda.getEscalas().isEmpty() ? null :demanda.getEscalas().stream().map(EscalaResponse::new).toList()
        );
    }
}

record EscalaResponse(
        UUID id,
        String funcaoEscala,
        Integer qtdColaborador,
        Integer horasJornada,
        Double valor,
        String nomeDemanda
) {
    public EscalaResponse(Escala escala) {
        this(
                escala.getId(),
                escala.getFuncaoEscala() != null ? FuncaoAlocacao.getValor(escala.getFuncaoEscala()) : null,
                escala.getQtdColaborador(),
                escala.getHorasJornada(),
                escala.getValor(),
                escala.getDemanda().getNome()
        );
    }
}