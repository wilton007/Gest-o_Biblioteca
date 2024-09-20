package com.wilton.biblioteca.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EmprestimoListResponseDto {

    private UsuarioResponseDto usuario;
    private List<EmprestimoResponseDto> livros;

    public EmprestimoListResponseDto(List<EmprestimoResponseDto> livros, UsuarioResponseDto usuario) {
        this.livros = livros;
        this.usuario = usuario;
    }

    public EmprestimoListResponseDto() {
    }
}
