package com.protasker.protasker_backend.dto.ProjectDto;


import com.protasker.protasker_backend.dto.UserDto.UserDto;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectTeamMemberDto  {
    private Long id;
    private Long projectId;
    private Long userId;
    private Long roleId;
    private String assignedAt;
}
