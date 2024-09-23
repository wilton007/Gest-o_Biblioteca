package com.wilton.biblioteca.services;

import com.wilton.biblioteca.dtos.LivroRequestDto;
import com.wilton.biblioteca.dtos.LivroResponseDto;
import com.wilton.biblioteca.exception.ExceptionPersonalizada;
import com.wilton.biblioteca.mappers.LivroMapper;
import com.wilton.biblioteca.model.Livro;
import com.wilton.biblioteca.repositorys.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @InjectMocks
    private LivroService service;
    @Mock
    private LivroRepository repository;
    @Mock
    private LivroMapper mapper;

    @Test
    void cadastrarLivroSucesso() {
        LivroRequestDto requestDto = new LivroRequestDto(1001, 10);
        Livro livro = new Livro();
        LivroResponseDto livroResponseDto = new LivroResponseDto();
        livroResponseDto.setTitulo("gelo e fogo");

        when(repository.existsById(1001L)).thenReturn(false);
        when(mapper.toLivro(requestDto)).thenReturn(livro);
        when(repository.save(livro)).thenReturn(livro);
        when(mapper.toLivroResponseDto(livro)).thenReturn(livroResponseDto);

        LivroResponseDto responseDto = service.cadastrarLivro(requestDto);

        assertNotNull(responseDto);
        assertEquals("gelo e fogo", responseDto.getTitulo());
        verify(repository, times(1)).existsById(1001L);
    }

    @Test
    void erroCadastrarLivro() {
        when(repository.existsById(1001L)).thenReturn(true);

        ExceptionPersonalizada exception = assertThrows(ExceptionPersonalizada.class, this::execute);

        assertNotNull(exception);
        assertEquals("ISBN já cadastrado", exception.getErros().get("mensagem"));
        assertEquals(409, exception.getStatus().value());
    }

    @Test
    void mostrarTodosLivrosSucesso() {
        Livro livro = new Livro();
        livro.setQuantidade(10);
        List<Livro> livros = List.of(livro);
        List<Object> objects = new ArrayList<>(livros);

        when(repository.findAll()).thenReturn(livros);
        when(mapper.toListLivros(livros)).thenReturn(objects);

        List<Object> listTest = service.mostrarTodosLivros();

        assertNotNull(listTest);
        assertFalse(listTest.isEmpty());
        verify(repository, times(1)).findAll();

    }

    @Test
    void mostrarTodosLivrosErro() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        ExceptionPersonalizada exception = assertThrows(ExceptionPersonalizada.class,
                () -> service.mostrarTodosLivros());

        assertNotNull(exception);
        assertEquals("lista vazia", exception.getErros().get("mensagem"));
        assertEquals(404, exception.getStatus().value());
    }

    private void execute() {
        service.cadastrarLivro(new LivroRequestDto(1001L, 10));
    }
}