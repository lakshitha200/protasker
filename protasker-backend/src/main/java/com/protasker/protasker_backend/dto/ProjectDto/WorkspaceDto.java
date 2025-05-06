package com.protasker.protasker_backend.dto.ProjectDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceDto {
    private Long id;
    private String workspaceId;
    private String name;
    private String slug;
    private String type;
    private String description;
    private String logoUrl;
    private String createdBy;
    private String clientId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
