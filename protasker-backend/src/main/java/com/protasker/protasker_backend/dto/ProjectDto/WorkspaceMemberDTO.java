package com.protasker.protasker_backend.dto.ProjectDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceMemberDTO {
    private String userId;
    private String role;
    private LocalDateTime joinedAt;
}
