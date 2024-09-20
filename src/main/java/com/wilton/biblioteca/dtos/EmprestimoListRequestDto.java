package com.wilton.biblioteca.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmprestimoListRequestDto {
    @NotNull
    private long idUsuario;
}
