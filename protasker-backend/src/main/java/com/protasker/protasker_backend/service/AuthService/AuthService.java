package com.protasker.protasker_backend.service.AuthService;

import com.protasker.protasker_backend.dto.AuthDto.PasswordResetDto;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.AuthDto.LoginRequestDto;
import com.protasker.protasker_backend.dto.AuthDto.RegisterRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    GenericResponseDto register(RegisterRequestDto registerRequest);
    GenericResponseDto login(LoginRequestDto loginRequest, HttpServletResponse response);

    GenericResponseDto refreshToken(HttpServletRequest request, HttpServletResponse response);

    void logout(String refreshToken);

    GenericResponseDto verifyEmail(String token);

    GenericResponseDto createResetToken(String email);

    GenericResponseDto resetPassword(PasswordResetDto passwordResetDto);

    GenericResponseDto resendVerifyEmail(String email);

    boolean checkAuthStatus(HttpServletRequest request);
}
