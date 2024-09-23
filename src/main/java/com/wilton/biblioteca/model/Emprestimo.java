package com.wilton.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmprestimo;
    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;
    @JoinColumn(name = "id_usuario", nullable = false)
    @ManyToOne
    private Usuario usuario;
    @NotNull
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo( Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
        this.livro = livro;
        this.usuario = usuario;
    }

    public Emprestimo() {
    }
}
