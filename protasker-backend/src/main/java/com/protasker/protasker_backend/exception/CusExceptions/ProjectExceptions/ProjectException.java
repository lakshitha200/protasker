package com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions;

public class ProjectException extends RuntimeException {
    public ProjectException(String message) {
        super(message);
    }
    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }

}
