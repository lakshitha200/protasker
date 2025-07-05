package com.protasker.protasker_backend.model.ReportsModel;

import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_id", nullable = false, unique = true, updatable = false)
    private String reportId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private ProjectTeamMember createdBy;

    private String status = "DRAFT";

    private String schedule; // "DAILY", "WEEKLY", "ON_DEMAND"

    @Column(columnDefinition = "json")
    private String metadata;

    @Column(columnDefinition = "json")
    private String parameters;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

