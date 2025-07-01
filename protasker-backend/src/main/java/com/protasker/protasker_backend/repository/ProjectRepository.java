package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.ProjectModel.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectId(String projectId);
    List<Project> findByWorkspace(Workspace workspace);
    List<Project> findByProjectManager(User projectManager);
}