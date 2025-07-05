package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.TaskModel.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskId(String taskId);
}
