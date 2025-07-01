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
public class WorkspaceUserId implements Serializable {
    private Long workspaceId;
    private Long userId;
}