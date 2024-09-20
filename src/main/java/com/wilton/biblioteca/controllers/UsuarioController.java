package com.wilton.biblioteca.controllers;

import com.wilton.biblioteca.dtos.*;
import com.wilton.biblioteca.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends BaseController{

    @Autowired
    UsuarioService service;

    @PostMapping
    public ResponseEntity<BaseResponseDto> cadastrarUsuario(@Valid @RequestBody UsuarioRequestDto requestDto){
        return sucess(service.salvarUsuario(requestDto));
    }

    @PostMapping("/fazer_emprestimo")
    public ResponseEntity<BaseResponseDto> emprestimoDeLivro(@Valid @RequestBody EmprestimoRequestDto requestDto){
        return sucess(service.pegarLivroEmprestado(requestDto));
    }

    @PostMapping("/devolver_emprestimo")
    public ResponseEntity<BaseResponseDto> devolverLivro(@RequestBody DevolucaoRequestDto requestDto){
        return sucess(service.devolverLivro(requestDto));
    }

    @GetMapping("/lista_de_emprestimo")
    public ResponseEntity<BaseResponseDto> listaEmprestimoDoUsuario(@Valid @RequestBody EmprestimoListRequestDto requestDto){
        return sucess(service.listDeEmprestimosDoUsuario(requestDto));
    }
}
