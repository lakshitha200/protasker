package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.TaskModel.TaskLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskLabelRepository extends JpaRepository<TaskLabel, Long> {
    Optional<TaskLabel> findByName(String name);
}
