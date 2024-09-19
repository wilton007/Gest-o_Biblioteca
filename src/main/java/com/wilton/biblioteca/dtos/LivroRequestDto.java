package com.wilton.biblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class LivroRequestDto {

    @NonNull
    private long isbn;
    @NotBlank
    private String titulo;
    @NotBlank
    private String autor;
    @NotBlank
    private String genero;
    @NonNull
    private int quantidade;

}
