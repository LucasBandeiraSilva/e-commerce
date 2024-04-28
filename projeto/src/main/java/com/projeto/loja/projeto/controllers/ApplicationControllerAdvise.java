package com.projeto.loja.projeto.controllers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.projeto.loja.projeto.exceptions.FuncionarioNotFoundException;
import com.projeto.loja.projeto.infra.RestErrorMessage;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApplicationControllerAdvise {
    @ExceptionHandler(FuncionarioNotFoundException.class)
    public ResponseEntity<RestErrorMessage> funcionarioNotFoundException(FuncionarioNotFoundException exception,
            HttpServletRequest request) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(Instant.now(), HttpStatus.NOT_FOUND.value(),
                "O funcionario não foi encontrado", exception.getMessage(),
                "http://localhost:8080" + request.getRequestURI());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }
}
