package com.protasker.protasker_backend.utils.Mapper.ProjectMapper;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
import com.protasker.protasker_backend.model.Client;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.WorkspaceModel.Workspace;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectDto toDto(Project entity) {
        if (entity == null) return null;

        return ProjectDto.builder()
                .id(entity.getId())
                .projectId(entity.getProjectId())
                .name(entity.getName())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .status(entity.getStatus())
                .clientId(entity.getClient() != null ? entity.getClient().getId() : null)
                .workspaceId(entity.getWorkspace() != null ? entity.getWorkspace().getId() : null)
                .priority(entity.getPriority())
                .projectManagerId(entity.getProjectManager() != null ? entity.getProjectManager().getId() : null)
                .projectType(entity.getProjectType())
                .methodology(entity.getMethodology())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public Project toEntity(ProjectDto dto) {
        if (dto == null) return null;

        Project entity = new Project();
        entity.setId(dto.getId());
        entity.setProjectId(dto.getProjectId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());

        Client client = new Client();
        client.setId(dto.getClientId());
        entity.setClient(client);

        Workspace workspace = new Workspace();
        workspace.setId(dto.getWorkspaceId());
        entity.setWorkspace(workspace);

        User manager = new User();
        manager.setId(dto.getProjectManagerId());
        entity.setProjectManager(manager);

        entity.setPriority(dto.getPriority());
        entity.setProjectType(dto.getProjectType());
        entity.setMethodology(dto.getMethodology());

        return entity;
    }
}