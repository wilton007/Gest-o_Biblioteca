package com.wilton.biblioteca.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmprestimoRequestDto {


    @NotNull
    private long isbn;
    @NotNull
    private long id_Usuario;

}
