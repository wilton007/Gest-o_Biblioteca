package com.wilton.biblioteca.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoResponseDto {


    private long id;
    private LocalDate dataEmprestimo;
    private UsuarioResponseDto usuario;
    private LivroResponseDto livro;

}
