package com.protasker.protasker_backend.dto;

import lombok.Setter;

import java.time.LocalDateTime;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean isSystemRole;
    private LocalDateTime createdAt;
}