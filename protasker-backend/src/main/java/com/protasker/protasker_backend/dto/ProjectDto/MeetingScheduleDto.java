package com.protasker.protasker_backend.dto.ProjectDto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingScheduleDto {
    private Long id;
    private Long projectId;
    private Long organizedById;
    private LocalDateTime meetingDate;
    private String title;
    private String description;
    private String meetingType;
    private String status;
    private String participants; // JSON string
}
