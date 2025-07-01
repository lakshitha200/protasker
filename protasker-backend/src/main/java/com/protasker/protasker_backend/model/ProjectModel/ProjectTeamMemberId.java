package com.protasker.protasker_backend.model.ProjectModel;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTeamMemberId implements Serializable {
    private Long project;  // Matches field name in ProjectTeamMember
    private Long user;
    private Long role;
}
