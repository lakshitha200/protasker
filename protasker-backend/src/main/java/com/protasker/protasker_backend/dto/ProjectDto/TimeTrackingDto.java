package com.protasker.protasker_backend.dto.ProjectDto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTrackingDto {
    private Long id;
    private String entityType;
    private Long entityId;
    private Long trackerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer durationMinutes;
    private String description;
}
