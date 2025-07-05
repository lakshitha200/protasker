package com.protasker.protasker_backend.model.WaterfallModel;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "phase_milestones")
public class PhaseMilestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phase_id", nullable = false)
    private ProjectPhase phase;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "actual_completion_date")
    private LocalDate actualCompletionDate;

    @Column(length = 20)
    private String status = "PENDING";

    @Column(name = "is_critical", nullable = false)
    private Boolean isCritical = false;

}
