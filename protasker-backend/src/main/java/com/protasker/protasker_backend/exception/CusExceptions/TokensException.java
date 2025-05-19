package com.protasker.protasker_backend.exception.CusExceptions;

import org.springframework.http.HttpStatus;

public class TokensException extends RuntimeException{
    public TokensException(String message, HttpStatus badRequest) {
        super(message);
    }

}
