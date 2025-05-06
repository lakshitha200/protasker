package com.protasker.protasker_backend.exception.GlobalExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleProductNotFoundException(IllegalArgumentException exception){

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Invalid Input");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        Map<String,String> errorResponse = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                (error)-> errorResponse.put(error.getField(),error.getDefaultMessage())
        );

        return errorResponse;
    }
}
