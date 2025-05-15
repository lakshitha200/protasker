package com.protasker.protasker_backend.dto.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateEmailDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
