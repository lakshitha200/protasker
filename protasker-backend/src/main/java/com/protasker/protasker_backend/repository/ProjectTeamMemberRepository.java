package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTeamMemberRepository extends JpaRepository<ProjectTeamMember, ProjectTeamMemberId> {
}
