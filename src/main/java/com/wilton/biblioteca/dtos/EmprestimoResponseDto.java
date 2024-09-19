package com.wilton.biblioteca.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoResponseDto {


    private long id;
    private LivroResponseDto livro;
    private UsuarioResponseDto usuario;
    private LocalDate dataEmprestimo;

}
