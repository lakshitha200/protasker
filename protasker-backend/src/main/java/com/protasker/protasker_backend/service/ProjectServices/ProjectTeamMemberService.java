package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectTeamMemberDto;

import java.util.List;

public interface ProjectTeamMemberService {
    ProjectTeamMemberDto assign(String projectId, ProjectTeamMemberDto dto);
    List<ProjectTeamMemberDto> getTeamByProject(String projectId);
    String remove(Long id);
}
