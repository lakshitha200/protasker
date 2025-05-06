package com.protasker.protasker_backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
public class GenericResponseDto {
    private String response;
    private HttpStatus code;
}
