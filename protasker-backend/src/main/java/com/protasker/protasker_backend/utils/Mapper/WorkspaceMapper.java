package com.protasker.protasker_backend.utils.Mapper;

import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceDto;
import com.protasker.protasker_backend.model.WorkspaceModel.Workspace;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.Client;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMapper {

    public WorkspaceDto toDto(Workspace workspace) {
        if (workspace == null) return null;
        return WorkspaceDto.builder()
                .id(workspace.getId())
                .workspaceId(workspace.getWorkspaceId())
                .name(workspace.getName())
                .slug(workspace.getSlug())
                .type(workspace.getType())
                .createdByUserId(workspace.getCreatedBy() != null ? workspace.getCreatedBy().getId() : null)
                .clientId(workspace.getClient() != null ? workspace.getClient().getId() : null)
                .description(workspace.getDescription())
                .logoUrl(workspace.getLogoUrl())
                .createdAt(workspace.getCreatedAt())
                .updatedAt(workspace.getUpdatedAt())
                .build();
    }

    public Workspace toEntity(WorkspaceDto dto, User createdBy, Client client) {
        if (dto == null) return null;
        Workspace workspace = new Workspace();
        workspace.setId(dto.getId());
        workspace.setWorkspaceId(dto.getWorkspaceId());
        workspace.setName(dto.getName());
        workspace.setSlug(dto.getSlug());
        workspace.setType(dto.getType());
        workspace.setCreatedBy(createdBy);
        workspace.setClient(client);
        workspace.setDescription(dto.getDescription());
        workspace.setLogoUrl(dto.getLogoUrl());
        return workspace;
    }
}
