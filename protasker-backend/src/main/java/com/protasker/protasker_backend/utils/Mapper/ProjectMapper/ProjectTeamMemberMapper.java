package com.protasker.protasker_backend.utils.Mapper.ProjectMapper;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectTeamMemberDto;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import com.protasker.protasker_backend.model.Role;
import com.protasker.protasker_backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectTeamMemberMapper {

    public ProjectTeamMember toEntity(ProjectTeamMemberDto dto) {
        return ProjectTeamMember.builder()
                .id(dto.getId())
                .project(Project.builder().id(dto.getProjectId()).build())
                .user(User.builder().id(dto.getUserId()).build())
                .role(Role.builder().id(dto.getRoleId()).build())
                .build();
    }

    public ProjectTeamMemberDto toDto(ProjectTeamMember entity) {
        return ProjectTeamMemberDto.builder()
                .id(entity.getId())
                .projectId(entity.getProject().getId())
                .userId(entity.getUser().getId())
                .roleId(entity.getRole().getId())
                .assignedAt(entity.getAssignedAt().toString())
                .build();
    }
}
