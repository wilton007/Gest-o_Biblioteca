package com.wilton.biblioteca.mappers;

import com.wilton.biblioteca.dtos.*;
import com.wilton.biblioteca.model.Emprestimo;
import com.wilton.biblioteca.model.Livro;
import com.wilton.biblioteca.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponseDto toUsuarioResponseDto(Usuario usuario);
    Usuario toUsuario(UsuarioRequestDto requestDto);

    List<Object> toListUsuarioResponseDto(List<Usuario> usuarios);


    EmprestimoResponseDto toEmprestimoResponseDto(Emprestimo emprestimo);


    List<Object> toEmprestimoListResponse(List<Emprestimo> list);

    DevolucaoResponseDto toDevolucaoResponseDto(Livro livro);
}
