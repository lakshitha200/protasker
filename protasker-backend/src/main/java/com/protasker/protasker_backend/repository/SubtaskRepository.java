package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.TaskModel.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    List<Subtask> findByTaskId(Long taskId);
}