package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
import com.protasker.protasker_backend.model.ProjectModel.Project;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto dto);
    ProjectDto getProjectById(String projectId);
    List<ProjectDto> getProjectsByWorkspaceId(Long workspaceId);
    void deleteProject(String projectId);
    ProjectDto updateProject(String projectId, ProjectDto dto);
}