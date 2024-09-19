package com.wilton.biblioteca.controllers;

import com.wilton.biblioteca.dtos.BaseResponseDto;
import com.wilton.biblioteca.dtos.LivroRequestDto;
import com.wilton.biblioteca.dtos.LivroResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class BaseController {

    protected final String SUCESSO = "sucesso!";
    protected final String ERRO = "erro!";

    protected ResponseEntity<BaseResponseDto> sucess(Object responseDto) {
        return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.value(), SUCESSO, responseDto));
    }

    protected ResponseEntity<List<BaseResponseDto>> listSucess(List<Object> responseDto) {
        List<BaseResponseDto> responseDtoList = new ArrayList<>();
        for (Object dto : responseDto) {
            responseDtoList.add(new BaseResponseDto(HttpStatus.OK.value(), SUCESSO, dto));
        }
        return ResponseEntity.ok(responseDtoList);
    }
}
