package com.protasker.protasker_backend.utils.Mapper.ProjectMapper;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectExpenseDto;
import com.protasker.protasker_backend.model.ProjectModel.ProjectExpense;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectExpenseMapper {

    public ProjectExpenseDto toDto(ProjectExpense entity) {
        if (entity == null) return null;

        return ProjectExpenseDto.builder()
                .id(entity.getId())
                .projectId(entity.getProject().getId())
                .amount(entity.getAmount())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .expenseDate(entity.getExpenseDate())
                .submittedByUserId(entity.getSubmittedBy() != null ? entity.getSubmittedBy().getId() : null)
                .approved(entity.getApproved())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public ProjectExpense toEntity(ProjectExpenseDto dto) {
        if (dto == null) return null;

        ProjectExpense entity = new ProjectExpense();

        Project project = new Project();
        project.setId(dto.getProjectId());
        entity.setProject(project);

        entity.setAmount(dto.getAmount());
        entity.setDescription(dto.getDescription());
        entity.setCategory(dto.getCategory());
        entity.setExpenseDate(dto.getExpenseDate());

        if (dto.getSubmittedByUserId() != null) {
            User user = new User();
            user.setId(dto.getSubmittedByUserId());
            entity.setSubmittedBy(user);
        } else {
            entity.setSubmittedBy(null);
        }

        entity.setApproved(dto.getApproved() != null ? dto.getApproved() : false);

        // createdAt handled by DB, no setting here

        return entity;
    }
}

