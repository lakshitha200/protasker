package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceDto;
import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceRequestDto;

public interface WorkspaceService {

    GenericResponseDto createWorkspace(WorkspaceRequestDto workspaceRequestDto, String userId);

    WorkspaceDto getWorkspace(String workspaceId);
}
