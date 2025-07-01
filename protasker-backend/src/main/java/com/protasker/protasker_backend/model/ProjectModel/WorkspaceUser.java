package com.protasker.protasker_backend.model.ProjectModel;

import com.protasker.protasker_backend.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "workspace_user")
public class WorkspaceUser {
    @EmbeddedId
    private WorkspaceUserId id;  // Composite key (workspace_id + user_id)

    @ManyToOne
    @MapsId("workspaceId")  // Links to workspace_id in composite key
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @ManyToOne
    @MapsId("userId")  // Links to user_id in composite key
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "join_at")
    private LocalDateTime joinAt;
}


