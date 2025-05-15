package com.protasker.protasker_backend.exception.CusExceptions;

public class UserServiceException extends RuntimeException{
    public UserServiceException(String message) {
        super(message);
    }
}
