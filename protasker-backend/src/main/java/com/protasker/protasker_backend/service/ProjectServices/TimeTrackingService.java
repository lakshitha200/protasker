package com.protasker.protasker_backend.service.ProjectServices;

import com.protasker.protasker_backend.dto.ProjectDto.TimeTrackingDto;

import java.util.List;

public interface TimeTrackingService {
    TimeTrackingDto create(TimeTrackingDto dto);
    List<TimeTrackingDto> getByEntity(String entityType, Long entityId);
    TimeTrackingDto update(Long id, TimeTrackingDto dto);
    String delete(Long id);
}

