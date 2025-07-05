package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.TimeTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface TimeTrackingRepository extends JpaRepository<TimeTracking, Long> {
    List<TimeTracking> findByEntityTypeAndEntityId(String entityType, Long entityId);
}
