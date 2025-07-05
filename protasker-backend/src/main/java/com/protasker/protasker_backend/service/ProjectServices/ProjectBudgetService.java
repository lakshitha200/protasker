package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectBudgetDto;
import com.protasker.protasker_backend.model.ProjectModel.ProjectBudget;

import java.util.List;
import java.util.stream.Collectors;

public interface ProjectBudgetService {
    public ProjectBudgetDto create(String projectId,ProjectBudgetDto dto);

    public ProjectBudgetDto update(Long id, ProjectBudgetDto dto);

    public String delete(Long id);

    public ProjectBudgetDto getById(Long id);

    public List<ProjectBudgetDto> getAllByProject(String projectId);
}
