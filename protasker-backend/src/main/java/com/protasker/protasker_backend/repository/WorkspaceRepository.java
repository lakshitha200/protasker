package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.WorkspaceModel.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkspaceRepository extends JpaRepository<Workspace,Long> {

    Optional<Workspace> findByWorkspaceId(String workspaceId);

    boolean existsBySlug(String slug);
}
