package com.protasker.protasker_backend.service;

import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceDto;

import java.util.List;

public interface WorkspaceService {
    WorkspaceDto createWorkspace(WorkspaceDto workspaceDto, String createdByUserId);
    WorkspaceDto updateWorkspace(String workspaceId, WorkspaceDto workspaceDto);
    WorkspaceDto getWorkspaceById(String workspaceId);
    List<WorkspaceDto> getAllWorkspaces();
    void deleteWorkspace(String workspaceId);
}

