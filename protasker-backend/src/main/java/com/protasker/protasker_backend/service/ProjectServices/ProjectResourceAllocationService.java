package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectResourceAllocationDto;

import java.util.List;

public interface ProjectResourceAllocationService {
    ProjectResourceAllocationDto create(ProjectResourceAllocationDto dto);
    List<ProjectResourceAllocationDto> getByTeamMember(Long teamMemberId);
    String delete(Long id);
}

