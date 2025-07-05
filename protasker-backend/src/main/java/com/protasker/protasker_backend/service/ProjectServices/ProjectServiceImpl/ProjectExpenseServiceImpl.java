package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectExpenseDto;
import com.protasker.protasker_backend.exception.CusExceptions.CustomNotFoundException;
import com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions.ProjectNotFoundException;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.ProjectModel.ProjectExpense;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.repository.ProjectExpenseRepository;

import com.protasker.protasker_backend.repository.ProjectRepository;
import com.protasker.protasker_backend.service.ProjectServices.ProjectExpenseService;
import com.protasker.protasker_backend.utils.Mapper.ProjectMapper.ProjectExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectExpenseServiceImpl implements ProjectExpenseService {

    private final ProjectExpenseRepository repository;
    private final ProjectExpenseMapper mapper;
    private final ProjectRepository projectRepository;


    @Transactional
    @Override
    public ProjectExpenseDto create(String projectId,ProjectExpenseDto dto) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: "+projectId));
        dto.setProjectId(project.getId());
        ProjectExpense entity = mapper.toEntity(dto);
        entity.setCreatedAt(Instant.now());
        ProjectExpense saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional
    @Override
    public ProjectExpenseDto update(Long id, ProjectExpenseDto dto) {
        ProjectExpense existing = repository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("ProjectExpense not found"));

        existing.setAmount(dto.getAmount());
        existing.setDescription(dto.getDescription());
        existing.setCategory(dto.getCategory());
        existing.setExpenseDate(dto.getExpenseDate());
        existing.setApproved(dto.getApproved() != null ? dto.getApproved() : existing.getApproved());
        // Optionally update submittedBy only if needed:
        if (dto.getSubmittedByUserId() != null) {
            User user = new User();
            user.setId(dto.getSubmittedByUserId());
            existing.setSubmittedBy(user);
        }

        ProjectExpense updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    @Transactional
    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "Project Expense was Deleted Successfully";
    }

    @Transactional
    @Override
    public ProjectExpenseDto getById(Long id) {
        ProjectExpense entity = repository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("ProjectExpense not found"));
        return mapper.toDto(entity);
    }

    @Transactional
    @Override
    public List<ProjectExpenseDto> getAllByProject(String projectId) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: "+projectId));
        List<ProjectExpense> expenses = repository.findByProjectId(project.getId());
        return expenses.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
