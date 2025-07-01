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
public class TeamMemberDto {
    private ProjectDto project;
    private UserDto user;
    private Role role;
    private LocalDateTime assignedAt;
}
