package com.wilton.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
public class Livro {

    @Id
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
    @OneToMany(mappedBy = "livro")
    private List<Emprestimo> emprestimos;

    public Livro() {
    }
}
