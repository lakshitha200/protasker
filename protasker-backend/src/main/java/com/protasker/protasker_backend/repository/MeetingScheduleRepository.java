package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.MeetingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface MeetingScheduleRepository extends JpaRepository<MeetingSchedule, Long> {
    List<MeetingSchedule> findByProjectId(Long projectId);
}
