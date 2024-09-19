package com.wilton.biblioteca.services;

import com.wilton.biblioteca.dtos.LivroRequestDto;
import com.wilton.biblioteca.dtos.LivroResponseDto;
import com.wilton.biblioteca.exception.ExceptionPersonalizada;
import com.wilton.biblioteca.mappers.LivroMapper;
import com.wilton.biblioteca.model.Livro;
import com.wilton.biblioteca.repositorys.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    LivroRepository repository;
    @Autowired
    LivroMapper mapper;

    public LivroResponseDto cadastrarLivro(LivroRequestDto requestDto) {
        verificarExistenciaIsbn(requestDto.getIsbn());
        return mapper.toLivroResponseDto(repository.save(mapper.toLivro(requestDto)));
    }

    public List<Object> showAllBooks() {
        return mapper.toListLivros(verificarListaSeEstaVazia());
    }


    private void verificarExistenciaIsbn(long isbn) {
        if (repository.existsById(isbn)) {
            throw new ExceptionPersonalizada("ISBN Existent", 409);
        }
    }

    private List<Livro> verificarListaSeEstaVazia() {
        List<Livro> livros = repository.findAll();
        if (livros.isEmpty()) {
            throw new ExceptionPersonalizada("lista vazia", 404);
        } else {
            return livros;
        }
    }

}
