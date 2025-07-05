package com.protasker.protasker_backend.repository;


import com.protasker.protasker_backend.dto.ProjectDto.ProjectResourceAllocationDto;
import com.protasker.protasker_backend.model.ProjectModel.ProjectResourceAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ProjectResourceAllocationRepository extends JpaRepository<ProjectResourceAllocation, Long> {
    List<ProjectResourceAllocation> findByProjectTeamMemberId(Long teamMemberId);
}
