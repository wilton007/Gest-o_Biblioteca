package com.wilton.biblioteca.dtos;

import lombok.Data;

@Data
public class DevolucaoRequestDto {

    private long idUsuario;
    private long idEmprestimo;
}
