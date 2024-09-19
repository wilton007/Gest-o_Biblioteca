package com.wilton.biblioteca.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DevolucaoResponseDto {

    private LivroResponseDto livro;
    private UsuarioResponseDto usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

}
