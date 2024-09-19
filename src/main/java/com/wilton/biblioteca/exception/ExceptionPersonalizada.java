package com.wilton.biblioteca.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ExceptionPersonalizada extends RuntimeException {
    private final Map<String, String> erros;
    private HttpStatus status;

    public ExceptionPersonalizada(String descricao, int status) {
        this.erros = new HashMap<>();
        this.status = HttpStatus.valueOf(status);
        erros.put("mensagem", descricao);
    }
}
