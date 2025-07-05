package com.protasker.protasker_backend.dto.WorkspaceDto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceDto {
    private Long id;
    private String workspaceId; // UUID string
    private String name;
    private String slug;
    private String type;
    private Long createdByUserId; // just user id reference
    private Long clientId;        // nullable
    private String description;
    private String logoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

