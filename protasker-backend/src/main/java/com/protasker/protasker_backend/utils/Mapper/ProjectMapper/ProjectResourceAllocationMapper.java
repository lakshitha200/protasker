package com.protasker.protasker_backend.utils.Mapper.ProjectMapper;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectResourceAllocationDto;
import com.protasker.protasker_backend.model.ProjectModel.ProjectResourceAllocation;
import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import org.springframework.stereotype.Component;

@Component
public class ProjectResourceAllocationMapper {

    public ProjectResourceAllocation toEntity(ProjectResourceAllocationDto dto) {
        return ProjectResourceAllocation.builder()
                .id(dto.getId())
                .projectTeamMember(ProjectTeamMember.builder().id(dto.getProjectTeamMemberId()).build())
                .allocationPercentage(dto.getAllocationPercentage())
                .allocationStart(dto.getAllocationStart())
                .allocationEnd(dto.getAllocationEnd())
                .build();
    }

    public ProjectResourceAllocationDto toDto(ProjectResourceAllocation entity) {
        return ProjectResourceAllocationDto.builder()
                .id(entity.getId())
                .projectTeamMemberId(entity.getProjectTeamMember().getId())
                .allocationPercentage(entity.getAllocationPercentage())
                .allocationStart(entity.getAllocationStart())
                .allocationEnd(entity.getAllocationEnd())
                .build();
    }
}

