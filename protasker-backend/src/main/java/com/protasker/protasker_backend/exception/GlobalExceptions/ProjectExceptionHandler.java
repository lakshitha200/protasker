package com.protasker.protasker_backend.exception.GlobalExceptions;

import com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions.ProjectNotFoundException;
import com.protasker.protasker_backend.exception.CusExceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ProblemDetail UserAlreadyExistsException(ProjectNotFoundException exception){

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Project Not Found Exception");
        problem.setDetail(exception.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
