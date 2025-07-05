package com.protasker.protasker_backend.dto.WorkspaceDto;

import com.protasker.protasker_backend.model.enums.WorkspaceRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WorkspaceUserDto {
    private Long workspaceId;
    private Long userId;
    private WorkspaceRole role;
    private LocalDateTime joinAt;
}
