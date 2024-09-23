package com.wilton.biblioteca.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoResponseDto {


    private long idEmprestimo;
    private LocalDate dataEmprestimo;
    private LivroResponseDto livro;

}
