package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ProjectTeamMemberRepository extends JpaRepository<ProjectTeamMember, Long> {
    boolean existsByProjectIdAndUserIdAndRoleId(Long projectId, Long userId, Long roleId);

    List<ProjectTeamMember> findByProjectId(Long id);
}
