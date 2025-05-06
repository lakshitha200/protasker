package com.protasker.protasker_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequestDto {

    @NotBlank(message = "Refresh Token is required")
    private String refreshToken;
}
