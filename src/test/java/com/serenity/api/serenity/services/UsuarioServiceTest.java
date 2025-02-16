package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;


    @Test
    @DisplayName("Nada no banco, lista vazia")
    void buscarTodos() {
        List<Usuario> lista = Collections.emptyList();
        Mockito.when(usuarioRepository.findAll()).thenReturn(lista);
        List<Usuario> resultado = usuarioService.listar();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("banco com informação, retorna lista com resultados")
    void buscarTodosListaCheia() {
        List<Usuario> usuarios = List.of(
                new Usuario(
                        UUID.randomUUID(),
                        "usuario@usuario.com",
                        "12345678",
                        1
                ),
                new Usuario(
                        UUID.randomUUID(),
                        "usuario1@usuario1.com",
                        "12345689",
                        0
                )
        );

        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.listar();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(usuarios.size(), resultado.size());
        assertArrayEquals(usuarios.toArray(), resultado.toArray());
    }


    @Test
    @DisplayName("Usuario pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        UUID id = UUID.randomUUID();
        Usuario usuarioEsperado = new Usuario(
                id,
                "usuario@usuario.com",
                "12345678",
                1
        );

        Mockito.when(usuarioRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(usuarioEsperado));
        Usuario resultado = usuarioService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(usuarioEsperado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> usuarioService.buscarPorId(id));

        assertEquals("O objeto usuario não foi encontrado(a)", ex.getMessage());
    }
}