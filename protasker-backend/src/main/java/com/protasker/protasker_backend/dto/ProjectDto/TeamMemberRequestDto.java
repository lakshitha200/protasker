package com.protasker.protasker_backend.dto.ProjectDto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberRequestDto {
    @NotNull(message = "Project Id cannot be null or empty")
    private String project_id;
    @NotNull(message = "User Id cannot be null or empty")
    private String user_id;
    @NotNull(message = "Role Id cannot be null or empty")
    private String role_id;
}
