package com.protasker.protasker_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetRequestDTO {

    @NotBlank(message = "Email is required")
    private String email;
}