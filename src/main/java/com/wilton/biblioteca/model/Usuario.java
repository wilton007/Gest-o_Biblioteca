package com.wilton.biblioteca.model;

import com.wilton.biblioteca.enums.UsuarioTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotNull
    private UsuarioTipo tipo;
    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimos;

}
