package com.protasker.protasker_backend.exception.GlobalExceptions;

import com.protasker.protasker_backend.exception.CusExceptions.FileStorageException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.net.URI;
import java.security.SignatureException;
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

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ProblemDetail handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception){

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Maximum upload size exceeded");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(DataAccessException.class)
    public ProblemDetail handleDatabaseErrors(DataAccessException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(500);
        problem.setTitle("Database Error");
        problem.setDetail(ex.getMessage());
        return problem;
    }

    @ExceptionHandler({
            ExpiredJwtException.class,
            UnsupportedJwtException.class,
            MalformedJwtException.class,
            SecurityException.class,  // Includes SignatureException
    })
    public ProblemDetail handleJwtExceptions(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("JWT Validation Failed");

        // Customize based on exception type
        if (ex instanceof ExpiredJwtException) {
            problemDetail.setDetail("Token has expired");
        } else if (ex instanceof UnsupportedJwtException) {
            problemDetail.setDetail("Unsupported JWT format");
        } else if (ex instanceof MalformedJwtException) {
            problemDetail.setDetail("Malformed JWT");
        } else if (ex instanceof SignatureException) {
            problemDetail.setDetail("Invalid JWT signature");
        }

        // Add custom properties if needed
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ProblemDetail handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Username Not Found");
        problem.setDetail(ex.getMessage());
        return problem;

    }


    @ExceptionHandler(FileStorageException.class)
    public ProblemDetail handleFileStorageException(FileStorageException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("File Storage Operation Failed");
        problem.setDetail("Could not complete file operation: " + ex.getMessage());
        problem.setProperty("errorCode", "FILE_STORAGE_ERROR");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }
}
