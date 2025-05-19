package com.protasker.protasker_backend.controller;

import com.protasker.protasker_backend.dto.AuthDto.PasswordResetDto;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.AuthDto.LoginRequestDto;
import com.protasker.protasker_backend.dto.AuthDto.RegisterRequestDto;
import com.protasker.protasker_backend.service.AuthService.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return new ResponseEntity<>(authService.register(registerRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequest, HttpServletResponse response) {
        return new ResponseEntity<>(authService.login(loginRequest,response), HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkAuthStatus(HttpServletRequest request) {
        return ResponseEntity.ok(authService.checkAuthStatus(request)); // Just validates the cookie
    }

    @PostMapping("/refresh")
    public ResponseEntity<GenericResponseDto> refresh(HttpServletRequest request,HttpServletResponse response) {
        return ResponseEntity.ok(authService.refreshToken(request,response));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam(name = "refreshToken") String refreshToken) {
        authService.logout(refreshToken);
        return ResponseEntity.ok().build();  // HTTP 200 OK response
    }

    @PostMapping("/verify-email")
    public ResponseEntity<GenericResponseDto> verifyEmail(@RequestBody Map<String,String> request) {
        return ResponseEntity.ok(authService.verifyEmail(request.get("token")));
    }

    @PostMapping("/resend-verification")
    public ResponseEntity<GenericResponseDto> resendVerifyEmail(@RequestBody Map<String,String> request) {
        return ResponseEntity.ok(authService.resendVerifyEmail(request.get("email")));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<GenericResponseDto> forgotPassword(@RequestBody Map<String,String> request) {
        return ResponseEntity.ok(authService.createResetToken(request.get("email")));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<GenericResponseDto> resetPassword(@RequestBody @Valid PasswordResetDto passwordResetDto) {
        return ResponseEntity.ok(authService.resetPassword(passwordResetDto));
    }
}

