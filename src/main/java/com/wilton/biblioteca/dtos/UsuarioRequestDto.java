package com.wilton.biblioteca.dtos;

import com.wilton.biblioteca.enums.UsuarioTipo;
import lombok.Data;

@Data
public class UsuarioRequestDto {

    private String nome;
    private String email;
    private String senha;
    private UsuarioTipo tipo;

}
