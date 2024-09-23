package com.wilton.biblioteca.controllers;

import com.wilton.biblioteca.dtos.BaseResponseDto;
import com.wilton.biblioteca.dtos.LivroRequestDto;
import com.wilton.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController extends BaseController {

    @Autowired
    LivroService service;

    @PostMapping
    public ResponseEntity<BaseResponseDto> criar(@Valid @RequestBody LivroRequestDto requestDto) {
        return sucess(service.cadastrarLivro(requestDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BaseResponseDto>> mostrarTodosLivros() {
        return listSucess(service.mostrarTodosLivros());
    }

}
