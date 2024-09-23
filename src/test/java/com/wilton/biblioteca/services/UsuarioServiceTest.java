package com.wilton.biblioteca.services;

import com.wilton.biblioteca.dtos.EmprestimoRequestDto;
import com.wilton.biblioteca.dtos.EmprestimoResponseDto;
import com.wilton.biblioteca.dtos.UsuarioRequestDto;
import com.wilton.biblioteca.dtos.UsuarioResponseDto;
import com.wilton.biblioteca.exception.ExceptionPersonalizada;
import com.wilton.biblioteca.mappers.UsuarioMapper;
import com.wilton.biblioteca.model.Emprestimo;
import com.wilton.biblioteca.model.Livro;
import com.wilton.biblioteca.model.Usuario;
import com.wilton.biblioteca.repositorys.EmprestimoRepository;
import com.wilton.biblioteca.repositorys.LivroRepository;
import com.wilton.biblioteca.repositorys.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService service;
    @Mock
    private UsuarioRepository repository;
    @Mock
    private LivroRepository livroRepository;
    @Mock
    private EmprestimoRepository emprestimoRepository;
    @Mock
    private UsuarioMapper mapper;

    @Test
    void salvarUsuarioSucesso() {
        UsuarioRequestDto requestDto = new UsuarioRequestDto();
        Usuario usuario = new Usuario();
        requestDto.setEmail("wilton@hotmail.com");

        when(repository.existsByEmail(requestDto.getEmail())).thenReturn(false);
        when(mapper.toUsuario(requestDto)).thenReturn(usuario);
        when(repository.save(usuario)).thenReturn(usuario);
        when(mapper.toUsuarioResponseDto(usuario)).thenReturn(new UsuarioResponseDto());

        UsuarioResponseDto responseDto = service.salvarUsuario(requestDto);

        assertNotNull(responseDto);
        assertEquals("wilton@hotmail.com", requestDto.getEmail());
        verify(repository, times(1)).existsByEmail(requestDto.getEmail());
        verify(repository, times(1)).save(usuario);

    }

    @Test
    void salvarUsuarioErro() {
        Usuario usuario = new Usuario();
        when(repository.existsByEmail("wilton@hotmail.com")).thenReturn(true);

        ExceptionPersonalizada exception = assertThrows(ExceptionPersonalizada.class, this::execute);

        assertNotNull(exception);
        assertEquals("Email já cadastrado", exception.getErros().get("mensagem"));
        assertEquals(409, exception.getStatus().value());

    }

    @Test
    void pegarLivroEmprestadoSucesso() {
        EmprestimoRequestDto requestDto = new EmprestimoRequestDto();
        requestDto.setIdUsuario(1L);
        requestDto.setIsbn(1001L);
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Livro livro = new Livro();
        livro.setQuantidade(10);
        livro.setIsbn(2L);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        List<Emprestimo> emprestimosList = List.of(emprestimo);
        usuario.setEmprestimos(emprestimosList);

        when(repository.findById(requestDto.getIdUsuario())).thenReturn(Optional.of(usuario));
        when(livroRepository.findById(1001L)).thenReturn(Optional.of(livro));
        when(emprestimoRepository.save(any())).thenReturn(emprestimo);
        when(mapper.toEmprestimoResponseDto(any())).thenReturn(new EmprestimoResponseDto());
        when(livroRepository.save(livro)).thenReturn(livro);

        EmprestimoResponseDto responseDto = service.pegarLivroEmprestado(requestDto);

        assertNotNull(responseDto);
        verify(livroRepository, times(1)).save(livro);
        verify(repository, times(2)).findById(requestDto.getIdUsuario());

    }


    private void execute() {
        UsuarioRequestDto requestDto = new UsuarioRequestDto();
        requestDto.setEmail("wilton@hotmail.com");
        service.salvarUsuario(requestDto);
    }

}