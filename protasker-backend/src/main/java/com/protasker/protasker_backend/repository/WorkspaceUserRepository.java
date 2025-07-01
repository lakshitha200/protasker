package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.WorkspaceUser;
import com.protasker.protasker_backend.model.ProjectModel.WorkspaceUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, WorkspaceUserId> {
}
