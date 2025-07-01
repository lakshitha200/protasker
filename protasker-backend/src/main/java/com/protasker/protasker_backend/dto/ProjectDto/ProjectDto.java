package com.protasker.protasker_backend.dto.ProjectDto;

import com.protasker.protasker_backend.dto.UserDto.UserDto;
import com.protasker.protasker_backend.model.Client;
import com.protasker.protasker_backend.model.ProjectModel.ProjectBudget;
import com.protasker.protasker_backend.model.ProjectModel.Workspace;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.enums.ProjectEnums.Methodology;
import com.protasker.protasker_backend.model.enums.ProjectEnums.Priority;
import com.protasker.protasker_backend.model.enums.ProjectEnums.ProjectStatus;
import com.protasker.protasker_backend.model.enums.ProjectEnums.ProjectType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private Long id;
    private String projectId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private Client client;
    private WorkspaceDto workspace;
    private Priority priority;
    private UserDto projectManager;
    private ProjectType projectType;
    private Methodology methodology;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private ProjectBudget budget;
}
