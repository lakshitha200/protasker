package com.protasker.protasker_backend.service;

import com.protasker.protasker_backend.dto.AuthResponseDto;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.LoginRequestDto;
import com.protasker.protasker_backend.dto.RegisterRequestDto;
import com.protasker.protasker_backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    GenericResponseDto register(RegisterRequestDto registerRequest);
    GenericResponseDto login(LoginRequestDto loginRequest, HttpServletResponse response);

    GenericResponseDto refreshToken(HttpServletRequest request, HttpServletResponse response);

    void logout(String refreshToken);

    GenericResponseDto verifyEmail(String token);

    GenericResponseDto createResetToken(String email);

    GenericResponseDto resetPassword(String token, String newPassword);

    GenericResponseDto resendVerifyEmail(String email);

    boolean checkAuthStatus(HttpServletRequest request);
}
