package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.ProjectBudget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectBudgetRepository extends JpaRepository<ProjectBudget, Long> {
    List<ProjectBudget> findByProjectId(Long projectId);
}
