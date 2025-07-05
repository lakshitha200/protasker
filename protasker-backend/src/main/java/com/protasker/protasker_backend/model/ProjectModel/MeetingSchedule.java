package com.protasker.protasker_backend.model.ProjectModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "meeting_schedules")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MeetingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Project FK
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    // Organizer FK (nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organized_by")
    private ProjectTeamMember organizedBy;

    @Column(name = "meeting_date", nullable = false)
    private LocalDateTime meetingDate;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "meeting_type", length = 50)
    private String meetingType; // Consider Enum if fixed values

    @Column(length = 50)
    private String status = "SCHEDULED";

    // JSON string storing participants IDs or details
    @Column(columnDefinition = "json")
    private String participants;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
