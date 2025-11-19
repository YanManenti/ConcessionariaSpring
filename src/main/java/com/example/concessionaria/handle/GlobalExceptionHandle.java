package com.example.concessionaria.handle;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidacoes(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("mensagem", "Erro de validação nos campos");
        response.put("erros", ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of(
                        "campo", error.getField(),
                        "mensagem", error.getDefaultMessage()
                )).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> outrasValidacoes(ConstraintViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("mensagem", "Erro de validação nos campos");
        response.put("erros", ex.getConstraintViolations()
                .stream()
                .map(error -> Map.of(
                        "campo", error.getPropertyPath().toString(),
                        "mensagem", error.getMessage()
                )).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
