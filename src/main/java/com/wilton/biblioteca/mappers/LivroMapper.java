package com.wilton.biblioteca.mappers;

import com.wilton.biblioteca.dtos.LivroRequestDto;
import com.wilton.biblioteca.dtos.LivroResponseDto;
import com.wilton.biblioteca.model.Livro;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro toLivro(LivroRequestDto requestDto);

    LivroResponseDto toLivroResponseDto(Livro livro);

    List<Object> toListLivros(List<Livro> livros);

}
