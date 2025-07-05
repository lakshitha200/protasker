package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.ProjectExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectExpenseRepository extends JpaRepository<ProjectExpense, Long> {
    List<ProjectExpense> findByProjectId(Long projectId);
}
