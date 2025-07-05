package com.protasker.protasker_backend.model.ProjectModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_resource_allocation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many allocations belong to one project team member
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_team_member_id", nullable = false)
    private ProjectTeamMember projectTeamMember;

    @Column(name = "allocation_percentage", precision = 5, scale = 2)
    private BigDecimal allocationPercentage;

    @Column(name = "allocation_start")
    private LocalDate allocationStart;

    @Column(name = "allocation_end")
    private LocalDate allocationEnd;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
