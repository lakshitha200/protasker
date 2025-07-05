package com.protasker.protasker_backend.repository;


import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUser;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, WorkspaceUserId> {
    Optional<WorkspaceUser> findByWorkspaceIdAndUserId(Long workspaceId, Long userId);
    boolean existsByWorkspaceIdAndUserId(Long workspaceId, Long userId);

    List<WorkspaceUser> findByWorkspaceId(Long workspaceId);
}
