package com.protasker.protasker_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


//    @Override
//    public void commence(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException authException) throws ServletException, IOException {
//
//
//        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
//        problem.setTitle("User Not Found");
//        problem.setDetail("JwtAuthenticationEntryPoint : Invalid password");
//        problem.setProperty("timestamp", LocalDateTime.now().toString());
//        String jsonResponse = new ObjectMapper().writeValueAsString(problem);
//
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write(jsonResponse);
//
//    }
@Override
public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

}
}
