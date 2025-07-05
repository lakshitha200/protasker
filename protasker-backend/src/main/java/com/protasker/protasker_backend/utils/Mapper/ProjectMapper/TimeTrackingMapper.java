package com.protasker.protasker_backend.utils.Mapper.ProjectMapper;

import com.protasker.protasker_backend.dto.ProjectDto.TimeTrackingDto;
import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import com.protasker.protasker_backend.model.ProjectModel.TimeTracking;
import org.springframework.stereotype.Component;

@Component
public class TimeTrackingMapper {

    public TimeTracking toEntity(TimeTrackingDto dto) {
        return TimeTracking.builder()
                .id(dto.getId())
                .entityType(dto.getEntityType())
                .entityId(dto.getEntityId())
                .tracker(dto.getTrackerId() != null ? ProjectTeamMember.builder().id(dto.getTrackerId()).build() : null)
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .durationMinutes(dto.getDurationMinutes())
                .description(dto.getDescription())
                .build();
    }

    public TimeTrackingDto toDto(TimeTracking entity) {
        return TimeTrackingDto.builder()
                .id(entity.getId())
                .entityType(entity.getEntityType())
                .entityId(entity.getEntityId())
                .trackerId(entity.getTracker() != null ? entity.getTracker().getId() : null)
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .durationMinutes(entity.getDurationMinutes())
                .description(entity.getDescription())
                .build();
    }
}
