package com.wilton.biblioteca.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EmprestimoListResponseDto {

    private UsuarioResponseDto usuario;
    private List<LivroResponseDto> livros;

}
