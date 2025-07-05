package com.protasker.protasker_backend.service;

import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceUserDto;

import java.util.List;

// WorkspaceUserService.java
public interface WorkspaceUserService {
    WorkspaceUserDto assignRole(WorkspaceUserDto dto);
    List<WorkspaceUserDto> getUsersByWorkspace(Long workspaceId);
    WorkspaceUserDto getById(Long workspaceId, Long userId);
    WorkspaceUserDto updateRole(Long workspaceId, Long userId, String newRole);
    String removeUser(Long workspaceId, Long userId);
}

