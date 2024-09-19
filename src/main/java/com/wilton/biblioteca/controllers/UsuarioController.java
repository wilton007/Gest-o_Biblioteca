package com.wilton.biblioteca.controllers;

import com.wilton.biblioteca.dtos.BaseResponseDto;
import com.wilton.biblioteca.dtos.EmprestimoListRequestDto;
import com.wilton.biblioteca.dtos.EmprestimoRequestDto;
import com.wilton.biblioteca.dtos.UsuarioRequestDto;
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

    @GetMapping("/lista_de_emprestimo")
    public ResponseEntity<List<BaseResponseDto>> listaEmprestimoDoUsuario(@Valid @RequestBody EmprestimoListRequestDto requestDto){
        return listSucess(service.listDeEmprestimosDoUsuario(requestDto));
    }
}
