package com.protasker.protasker_backend.utils.Mapper.ProjectMapper;

import com.protasker.protasker_backend.dto.ProjectDto.MeetingScheduleDto;
import com.protasker.protasker_backend.model.ProjectModel.MeetingSchedule;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import org.springframework.stereotype.Component;

@Component
public class MeetingScheduleMapper {

    public MeetingSchedule toEntity(MeetingScheduleDto dto) {
        return MeetingSchedule.builder()
                .id(dto.getId())
                .project(Project.builder().id(dto.getProjectId()).build())
                .organizedBy(dto.getOrganizedById() != null ? ProjectTeamMember.builder().id(dto.getOrganizedById()).build() : null)
                .meetingDate(dto.getMeetingDate())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .meetingType(dto.getMeetingType())
                .status(dto.getStatus())
                .participants(dto.getParticipants())
                .build();
    }

    public MeetingScheduleDto toDto(MeetingSchedule entity) {
        return MeetingScheduleDto.builder()
                .id(entity.getId())
                .projectId(entity.getProject().getId())
                .organizedById(entity.getOrganizedBy() != null ? entity.getOrganizedBy().getId() : null)
                .meetingDate(entity.getMeetingDate())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .meetingType(entity.getMeetingType())
                .status(entity.getStatus())
                .participants(entity.getParticipants())
                .build();
    }
}
