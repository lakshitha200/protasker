package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.ProjectDto.MeetingScheduleDto;

import java.util.List;

public interface MeetingScheduleService {
    MeetingScheduleDto create(MeetingScheduleDto dto);
    List<MeetingScheduleDto> getByProject(Long projectId);
    String cancel(Long id);
}
