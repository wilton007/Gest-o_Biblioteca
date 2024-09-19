package com.wilton.biblioteca.advice;

import com.wilton.biblioteca.dtos.BaseResponseDto;
import com.wilton.biblioteca.exception.ExceptionPersonalizada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AdviceException {
    private final String ERRO = "erro";

    @ExceptionHandler(ExceptionPersonalizada.class)
    public ResponseEntity<BaseResponseDto> exceptionPersonalizada(ExceptionPersonalizada personalizada) {
        Map<String, String> erros = new HashMap<>();
        erros.put("mensagem", personalizada.getErros().get("mensagem"));
        return ResponseEntity.status(personalizada.getStatus())
                .body(new BaseResponseDto(personalizada.getStatus().value(), ERRO, erros));
    }
}
