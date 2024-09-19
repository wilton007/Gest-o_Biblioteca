package com.wilton.biblioteca.dtos;

import lombok.Data;

@Data
public class BaseResponseDto {
    private Integer codigo;
    private String mensagem;
    private Object dados;

    public BaseResponseDto(Integer codigo, String mensagem, Object dados) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.dados = dados;
    }
}
