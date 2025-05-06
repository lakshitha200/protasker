package com.protasker.protasker_backend.controller;

import com.protasker.protasker_backend.dto.AuthResponseDto;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.LoginRequestDto;
import com.protasker.protasker_backend.dto.RegisterRequestDto;
import com.protasker.protasker_backend.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return new ResponseEntity<>(authService.register(registerRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(@RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam(name = "refreshToken") String refreshToken) {
        authService.logout(refreshToken);
        return ResponseEntity.ok().build();  // HTTP 200 OK response
    }

    @GetMapping("/verify-email")
    public ResponseEntity<GenericResponseDto> verifyEmail(@RequestParam String token) {
        System.out.println(token);
        return ResponseEntity.ok(authService.verifyEmail(token));
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
    public ResponseEntity<GenericResponseDto> resetPassword(@RequestBody Map<String,String> request) {
        return ResponseEntity.ok(authService.resetPassword(request.get("token"), request.get("newPassword")));
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request){
        System.out.println("Request received from: "+ request.getRemoteAddr());
        System.out.println("User-Agent: "+ request.getHeader("User-Agent"));
        System.out.println("Referer: "+ request.getHeader("Referer"));
        System.out.println("Mehod : "+ request.getMethod());
        return "Test Login Success";
    }
}

