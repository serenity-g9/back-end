package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Pagamento;
import com.serenity.api.serenity.repositories.PagamentoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {
    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;


    @Test
    @DisplayName("Nada no banco, lista vazia")
    void buscarTodos() {
        List<Pagamento> lista = Collections.emptyList();
        Mockito.when(pagamentoRepository.findAll()).thenReturn(lista);
        List<Pagamento> resultado = pagamentoService.listar();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("banco com informação, retorna lista com resultados")
    void buscarTodosListaCheia() {
        List<Pagamento> Pagamentos = List.of(
                new Pagamento(
                        UUID.randomUUID(),
                        20.0,
                        LocalDateTime.now(),
                        LocalDate.now().plusMonths(6),
                        false,
                        new Agendamento()
                ),
                new Pagamento(
                        UUID.randomUUID(),
                        20.0,
                        LocalDateTime.now(),
                        LocalDate.now().plusMonths(6),
                        false,
                        new Agendamento()
                )
        );

        Mockito.when(pagamentoRepository.findAll()).thenReturn(Pagamentos);

        List<Pagamento> resultado = pagamentoService.listar();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(Pagamentos.size(), resultado.size());
        assertArrayEquals(Pagamentos.toArray(), resultado.toArray());
    }


    @Test
    @DisplayName("Pagamento pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        UUID id = UUID.randomUUID();
        Pagamento PagamentoEsperado = new Pagamento(
                id,
                20.0,
                LocalDateTime.now(),
                LocalDate.now().plusMonths(6),
                false,
                new Agendamento()
        );

        Mockito.when(pagamentoRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(PagamentoEsperado));
        Pagamento resultado = pagamentoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(PagamentoEsperado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(pagamentoRepository.findById(id)).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> pagamentoService.buscarPorId(id));

        assertEquals("O objeto pagamento não foi encontrado(a)", ex.getMessage());
    }

}