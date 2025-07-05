package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectExpenseDto;

import java.util.List;

public interface ProjectExpenseService {
    ProjectExpenseDto create(String projectId,ProjectExpenseDto dto);
    ProjectExpenseDto update(Long id, ProjectExpenseDto dto);
    String delete(Long id);
    ProjectExpenseDto getById(Long id);
    List<ProjectExpenseDto> getAllByProject(String projectId);
}
