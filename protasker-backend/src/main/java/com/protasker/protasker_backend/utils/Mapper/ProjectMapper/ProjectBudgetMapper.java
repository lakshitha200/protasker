package com.protasker.protasker_backend.utils.Mapper.ProjectMapper;


import com.protasker.protasker_backend.dto.ProjectDto.ProjectBudgetDto;
import com.protasker.protasker_backend.model.ProjectModel.ProjectBudget;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectBudgetMapper {

    public ProjectBudgetDto toDto(ProjectBudget entity) {
        if (entity == null) return null;

        return ProjectBudgetDto.builder()
                .id(entity.getId())
                .projectId(entity.getProject().getId())
                .totalAmount(entity.getTotalAmount())
                .currency(entity.getCurrency())
                .allocatedByUserId(entity.getAllocatedBy().getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public ProjectBudget toEntity(ProjectBudgetDto dto) {
        if (dto == null) return null;

        ProjectBudget entity = new ProjectBudget();

        Project project = new Project();
        project.setId(dto.getProjectId());
        entity.setProject(project);

        entity.setTotalAmount(dto.getTotalAmount());
        entity.setCurrency(dto.getCurrency() != null ? dto.getCurrency() : "USD");

        User user = new User();
        user.setId(dto.getAllocatedByUserId());
        entity.setAllocatedBy(user);

        return entity;
    }
}
