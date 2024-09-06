package com.example.movement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleGenericException(Exception ex) {
        return ErrorResponse.builder(ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage()).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return ErrorResponse.builder(ex,
                HttpStatus.BAD_REQUEST,
                ex.getMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotFoundException(IllegalArgumentException ex) {
        return ErrorResponse.builder(ex,
                HttpStatus.BAD_REQUEST,
                ex.getMessage()).build();
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleAlreadyExistException(AlreadyExistException ex) {
        return ErrorResponse.builder(ex,
                HttpStatus.BAD_REQUEST,
                ex.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> error.getDefaultMessage()
                ));

        return ResponseEntity.badRequest().body(errors);
    }
}
