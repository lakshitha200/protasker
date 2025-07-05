package com.protasker.protasker_backend.model.TaskModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_labels")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TaskLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String color;
}

