package com.protasker.protasker_backend.security;

import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceDto;
import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceUserDto;
import com.protasker.protasker_backend.exception.CusExceptions.ForbiddenException;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUser;

public class WorkspacePermissionChecker {

    public static void checkUpdateRole(Long currentUserId, User targetUser, WorkspaceUserDto workspaceUserDto) {
        if (workspaceUserDto.getRole()!=null && !workspaceUserDto.getRole().equals("")
        && workspaceUserDto.getRole().toString().equals("SUPER_ADMIN")) {
            throw new ForbiddenException("Cannot update Super Admin role.");
        }

        if (currentUserId.toString().equals(targetUser.getId().toString())) {
            throw new ForbiddenException("You cannot update your own role.");
        }
    }

    public static void checkDeleteUser(WorkspaceUser currentUser, WorkspaceUser targetUser) {
        if (targetUser.getRole().equals("SUPER_ADMIN")) {
            throw new ForbiddenException("Cannot delete Super Admin.");
        }

        if (currentUser.getId().equals(targetUser.getId()) &&
                (currentUser.getRole().equals("ADMIN") || currentUser.getRole().equals("SUPER_ADMIN"))) {
            throw new ForbiddenException("You cannot delete yourself.");
        }

        if (currentUser.getRole().equals("ADMIN") &&
                (targetUser.getRole().equals("ADMIN") || targetUser.getRole().equals("SUPER_ADMIN"))) {
            throw new ForbiddenException("Admin cannot delete other Admins or Super Admins.");
        }
    }

    public static void checkRemoveSelf(WorkspaceUser currentUser, WorkspaceUser targetUser) {
        if (!currentUser.getId().equals(targetUser.getId())) {
            throw new ForbiddenException("You can only remove yourself.");
        }
    }
}

