package com.protasker.protasker_backend.model.WorkspaceModel;

import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.enums.WorkspaceRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"workspace", "user"})
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private WorkspaceRole role;

    @CreationTimestamp
    @Column(name = "join_at", nullable = false, updatable = false)
    private LocalDateTime joinAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}


