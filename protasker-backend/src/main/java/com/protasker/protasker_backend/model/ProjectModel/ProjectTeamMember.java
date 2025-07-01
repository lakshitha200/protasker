package com.protasker.protasker_backend.model.ProjectModel;

import com.protasker.protasker_backend.model.Role;
import com.protasker.protasker_backend.model.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "project_team_members")
public class ProjectTeamMember {
    @EmbeddedId
    private ProjectTeamMemberId id;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @CreationTimestamp
    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;
}


