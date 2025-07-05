package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;



import com.protasker.protasker_backend.dto.ProjectDto.ProjectBudgetDto;
import com.protasker.protasker_backend.exception.CusExceptions.CustomNotFoundException;
import com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions.ProjectNotFoundException;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.ProjectModel.ProjectBudget;
import com.protasker.protasker_backend.repository.ProjectBudgetRepository;
import com.protasker.protasker_backend.repository.ProjectRepository;
import com.protasker.protasker_backend.service.ProjectServices.ProjectBudgetService;
import com.protasker.protasker_backend.utils.Mapper.ProjectMapper.ProjectBudgetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectBudgetServiceImpl implements ProjectBudgetService {

    private final ProjectBudgetRepository repository;
    private final ProjectRepository projectRepository;
    private final ProjectBudgetMapper mapper;


    @Transactional
    @Override
    public ProjectBudgetDto create(String projectId,ProjectBudgetDto dto) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: "+projectId));
        dto.setProjectId(project.getId());
        ProjectBudget entity = mapper.toEntity(dto);
        ProjectBudget saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional
    @Override
    public ProjectBudgetDto update(Long id, ProjectBudgetDto dto) {
        ProjectBudget entity = repository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("ProjectBudget not found"));

        entity.setTotalAmount(dto.getTotalAmount());
        entity.setCurrency(dto.getCurrency());
        // Update project and allocatedBy only if needed:
        // existing.setProject(new Project(dto.getProjectId()));
        // existing.setAllocatedBy(new User(dto.getAllocatedByUserId()));

        ProjectBudget updated = repository.save(entity);
        return mapper.toDto(updated);
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "Project Budget was Deleted Successfully";
    }

    @Transactional
    @Override
    public ProjectBudgetDto getById(Long id) {
        ProjectBudget entity = repository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("ProjectBudget not found"));
        return mapper.toDto(entity);
    }

    @Transactional
    @Override
    public List<ProjectBudgetDto> getAllByProject(String projectId) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: "+projectId));
        List<ProjectBudget> budgets = repository.findByProjectId(project.getId());
        return budgets.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}

