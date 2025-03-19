package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.EscalaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EscalaServiceTest {

    @Mock
    private EscalaRepository escalaRepository;

    @InjectMocks
    private EscalaService escalaService;


    @Test
    @DisplayName("Nada no banco, lista vazia")
    void buscarTodos() {
        List<Escala> lista = Collections.emptyList();
        Mockito.when(escalaRepository.findAll()).thenReturn(lista);
        List<Escala> resultado = escalaService.listar();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("banco com informação, retorna lista com resultados")
    void buscarTodosListaCheia() {
        List<Escala> escalas = List.of(
                new Escala(
                        UUID.randomUUID(),
                        11,
                        5,
                        40,
                        102,
                        102,
                        2500.0,
                        new Demanda(),
                        List.of(new Agendamento())
                ),
                new Escala(
                        UUID.randomUUID(),
                        7,
                        8,
                        50,
                        202,
                        202,
                        4500.0,
                        new Demanda(),
                        List.of(new Agendamento())
                )
        );

        Mockito.when(escalaRepository.findAll()).thenReturn(escalas);

        List<Escala> resultado = escalaService.listar();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(escalas.size(), resultado.size());
        assertArrayEquals(escalas.toArray(), resultado.toArray());
    }



    @Test
    @DisplayName("Usuario pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        UUID id = UUID.randomUUID();
        Escala escalaEsperada = new Escala(
                id,
                7,
                8,
                50,
                102,
                102,
                4500.0,
                new Demanda(),
                List.of(new Agendamento())
        );

        Mockito.when(escalaRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(escalaEsperada));
        Escala resultado = escalaService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(escalaEsperada.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(escalaRepository.findById(id)).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> escalaService.buscarPorId(id));

        assertEquals("O objeto escala não foi encontrado(a)", ex.getMessage());
    }

}