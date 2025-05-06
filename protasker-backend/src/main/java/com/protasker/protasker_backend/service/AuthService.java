package com.protasker.protasker_backend.service;

import com.protasker.protasker_backend.dto.AuthResponseDto;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.LoginRequestDto;
import com.protasker.protasker_backend.dto.RegisterRequestDto;
import com.protasker.protasker_backend.model.User;

public interface AuthService {
    GenericResponseDto register(RegisterRequestDto registerRequest);
    AuthResponseDto login(LoginRequestDto loginRequest);

    AuthResponseDto refreshToken(String refreshToken);

    void logout(String refreshToken);

    GenericResponseDto verifyEmail(String token);

    GenericResponseDto createResetToken(String email);

    GenericResponseDto resetPassword(String token, String newPassword);

    GenericResponseDto resendVerifyEmail(String email);
}
