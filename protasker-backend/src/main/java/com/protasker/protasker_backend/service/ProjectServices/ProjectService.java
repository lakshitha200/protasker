package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
import com.protasker.protasker_backend.dto.ProjectDto.ProjectRequestDto;
import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ProjectService {
    GenericResponseDto createProject(ProjectRequestDto request);

    ProjectDto getProject(String projectId);

    List<ProjectDto> getAllProjects();
}
