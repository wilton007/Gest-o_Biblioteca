package com.wilton.biblioteca.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EmprestimoListResponseDto {

    private long idUsuario;
    private List<EmprestimoResponseDto> livros;

    public EmprestimoListResponseDto(List<EmprestimoResponseDto> livros, long idUsuario) {
        this.livros = livros;
        this.idUsuario = idUsuario;
    }

    public EmprestimoListResponseDto() {
    }
}
