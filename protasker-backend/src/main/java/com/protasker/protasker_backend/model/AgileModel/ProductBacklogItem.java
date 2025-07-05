package com.protasker.protasker_backend.model.AgileModel;

import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import com.protasker.protasker_backend.model.Role;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_backlog_items")
public class ProductBacklogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String priority = "MEDIUM";

    @Column(name = "story_points")
    private Integer storyPoints = 0;

    @Column(length = 255)
    private String status = "NEW";

    @Column(length = 255)
    private String type = "USER_STORY";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private ProjectTeamMember createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Backlog items that this item depends on
    @ManyToMany
    @JoinTable(
            name = "backlog_item_dependencies",
            joinColumns = @JoinColumn(name = "backlog_item_id"),
            inverseJoinColumns = @JoinColumn(name = "depends_on_item_id")
    )
    private Set<ProductBacklogItem> dependsOnItems;

    // Backlog items that depend on this item
    @ManyToMany(mappedBy = "dependsOnItems")
    private Set<ProductBacklogItem> dependentItems;

}
