package com.protasker.protasker_backend.exception.GlobalExceptions;

import com.protasker.protasker_backend.exception.CusExceptions.AuthException;
import com.protasker.protasker_backend.exception.CusExceptions.RegistrationException;
import com.protasker.protasker_backend.exception.CusExceptions.TokensException;
import com.protasker.protasker_backend.exception.CusExceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class AuthServiceExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail UserAlreadyExistsException(UserAlreadyExistsException exception){

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("User Already Exists");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(RegistrationException.class)
    public ProblemDetail handleRegistrationException(RegistrationException exception){

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setType(URI.create("http://localhost:8080/api/auth/register"));
        problem.setTitle("Registration Failed");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(AuthException.class)
    public ProblemDetail handleAuthException(AuthException exception){

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problem.setType(URI.create("http://localhost:8080/api/auth/login"));
        problem.setTitle("Login Failed");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }


    @ExceptionHandler(TokensException.class)
    public ProblemDetail handleAuthException(TokensException exception){

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
//        problem.setType(URI.create("http://localhost:8080/api/auth/login"));
        problem.setTitle("Token Error");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
