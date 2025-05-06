package com.protasker.protasker_backend.exception.GlobalExceptions;

import com.protasker.protasker_backend.exception.CusExceptions.AuthException;
import com.protasker.protasker_backend.exception.CusExceptions.UserAlreadyExistsException;
import com.protasker.protasker_backend.exception.CusExceptions.UserManagementException;
import com.protasker.protasker_backend.exception.CusExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException exception){

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
//        problem.setType(URI.create("http://localhost:8080/api/auth/login"));
        problem.setTitle("User Not Found");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail UserAlreadyExistsException(UserAlreadyExistsException exception){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("User Already Exists");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

}
