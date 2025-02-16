package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Codigo;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.AgendamentoRepository;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @InjectMocks
    private AgendamentoService agendamentoService;


    @Test
    @DisplayName("Nada no banco, lista vazia")
    void buscarTodos() {
        List<Agendamento> lista = Collections.emptyList();
        Mockito.when(agendamentoRepository.findAll()).thenReturn(lista);
        List<Agendamento> resultado = agendamentoService.listar();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("banco com informação, retorna lista com resultados")
    void buscarTodosListaCheia() {
        UUID id = UUID.randomUUID();
        List<Agendamento> Agendamentos = List.of(
                new Agendamento(
                        id,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(2),
                        LocalDateTime.now().plusDays(2),
                        new Escala(),
                        new Usuario(),
                        new Codigo(),
                        new Codigo()),
                new Agendamento(
                        id,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(2),
                        LocalDateTime.now().plusDays(2),
                        new Escala(),
                        new Usuario(),
                        new Codigo(),
                        new Codigo())
        );

        Mockito.when(agendamentoRepository.findAll()).thenReturn(Agendamentos);

        List<Agendamento> resultado = agendamentoService.listar();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(Agendamentos.size(), resultado.size());
        assertArrayEquals(Agendamentos.toArray(), resultado.toArray());
    }

    @Test
    @DisplayName("Agendamento pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        UUID id = UUID.randomUUID();
        Agendamento AgendamentoEsperado = new Agendamento(
                id,
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(2),
                LocalDateTime.now().plusDays(2),
                new Escala(),
                new Usuario(),
                new Codigo(),
                new Codigo()
        );

        Mockito.when(agendamentoRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(AgendamentoEsperado));
        Agendamento resultado = agendamentoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(AgendamentoEsperado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(agendamentoRepository.findById(id)).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> agendamentoService.buscarPorId(id));

        assertEquals("O objeto agendamento não foi encontrado(a)", ex.getMessage());
    }

}