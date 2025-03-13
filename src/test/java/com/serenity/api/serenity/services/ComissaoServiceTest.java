package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.*;
import com.serenity.api.serenity.repositories.ComissaoRepository;
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
class ComissaoServiceTest {
    @Mock
    private ComissaoRepository comissaoRepository;

    @InjectMocks
    private ComissaoService ComissaoService;


    @Test
    @DisplayName("Nada no banco, lista vazia")
    void buscarTodos() {
        List<Comissao> lista = Collections.emptyList();
        Mockito.when(comissaoRepository.findAll()).thenReturn(lista);
        List<Comissao> resultado = ComissaoService.listar();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("banco com informação, retorna lista com resultados")
    void buscarTodosListaCheia() {
        List<Comissao> Comissaos = List.of(
                new Comissao( UUID.randomUUID(),
                        "item0",
                        500.0,
                        1,
                        LocalDateTime.now(),
                        LocalDate.now().plusMonths(12),
                        false,
                        new Agendamento()
                ),
                new Comissao(
                        UUID.randomUUID(),
                        "item1",
                        500.0,
                        1,
                        LocalDateTime.now(),
                        LocalDate.now().plusMonths(12),
                        false,
                        new Agendamento()
                )
        );

        Mockito.when(comissaoRepository.findAll()).thenReturn(Comissaos);

        List<Comissao> resultado = ComissaoService.listar();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(Comissaos.size(), resultado.size());
        assertArrayEquals(Comissaos.toArray(), resultado.toArray());
    }


    @Test
    @DisplayName("Comissao pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        UUID id = UUID.randomUUID();
        Comissao ComissaoEsperado = new Comissao(
                UUID.randomUUID(),
                "item1",
                500.0,
                1,
                LocalDateTime.now(),
                LocalDate.now().plusMonths(12),
                false,
                new Agendamento()
        );

        Mockito.when(comissaoRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(ComissaoEsperado));
        Comissao resultado = ComissaoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(ComissaoEsperado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(comissaoRepository.findById(id)).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> ComissaoService.buscarPorId(id));

        assertEquals("O objeto comissao não foi encontrado(a)", ex.getMessage());
    }
}