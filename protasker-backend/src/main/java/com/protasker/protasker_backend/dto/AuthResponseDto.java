package com.protasker.protasker_backend.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponseDto {
    private String responseMessage;
    private String accessToken = "NA";
    private String refreshToken = "NA";
}
