package com.protasker.protasker_backend.utils.Mapper;

import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceUserDto;
import com.protasker.protasker_backend.model.WorkspaceModel.Workspace;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUser;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUserId;
import org.springframework.stereotype.Component;


@Component
public class WorkspaceUserMapper {

    public WorkspaceUserDto toDto(WorkspaceUser entity) {
        return WorkspaceUserDto.builder()
                .workspaceId(entity.getWorkspace().getId())
                .userId(entity.getUser().getId())
                .role(entity.getRole())
                .joinAt(entity.getJoinAt())
                .build();
    }

    public WorkspaceUser toEntity(Workspace workspace, User user, WorkspaceUserDto dto) {
        WorkspaceUserId id = new WorkspaceUserId(workspace.getId(), user.getId());
        return WorkspaceUser.builder()
                .id(id)
                .workspace(workspace)
                .user(user)
                .role(dto.getRole())
                .build();
    }
}

