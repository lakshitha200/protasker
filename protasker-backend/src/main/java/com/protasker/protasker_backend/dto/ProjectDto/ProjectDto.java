package com.protasker.protasker_backend.dto.ProjectDto;

import com.protasker.protasker_backend.dto.UserDto.UserDto;
import com.protasker.protasker_backend.model.Client;
import com.protasker.protasker_backend.model.enums.ProjectEnums.Methodology;
import com.protasker.protasker_backend.model.enums.ProjectEnums.Priority;
import com.protasker.protasker_backend.model.enums.ProjectEnums.ProjectStatus;
import com.protasker.protasker_backend.model.enums.ProjectEnums.ProjectType;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String projectId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private Long clientId;
    private Long workspaceId;
    private Priority priority;
    private Long projectManagerId;
    private ProjectType projectType;
    private Methodology methodology;
    private Instant createdAt;
    private Instant updatedAt;
}
