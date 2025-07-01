package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.ProjectBudget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectBudgetRepository extends JpaRepository<ProjectBudget, Long> {
}
