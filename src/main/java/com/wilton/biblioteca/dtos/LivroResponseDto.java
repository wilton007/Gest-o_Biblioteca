package com.wilton.biblioteca.dtos;

import lombok.Data;

@Data
public class LivroResponseDto {

    private long isbn;
    private String titulo;
    private String autor;
    private String genero;
//    private int quantidade;

}
