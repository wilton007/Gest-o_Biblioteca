package com.wilton.biblioteca.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DevolucaoResponseDto {

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private long idUsuario;
    private long isbn;

}
