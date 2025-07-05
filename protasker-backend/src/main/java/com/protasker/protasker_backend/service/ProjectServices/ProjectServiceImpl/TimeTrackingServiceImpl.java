package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;

import com.protasker.protasker_backend.dto.ProjectDto.TimeTrackingDto;
import com.protasker.protasker_backend.model.ProjectModel.TimeTracking;
import com.protasker.protasker_backend.repository.TimeTrackingRepository;
import com.protasker.protasker_backend.service.ProjectServices.TimeTrackingService;
import com.protasker.protasker_backend.utils.Mapper.ProjectMapper.TimeTrackingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeTrackingServiceImpl implements TimeTrackingService {

    private final TimeTrackingRepository repository;
    private final TimeTrackingMapper mapper;

    @Override
    public TimeTrackingDto create(TimeTrackingDto dto) {
        TimeTracking saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public List<TimeTrackingDto> getByEntity(String entityType, Long entityId) {
        return repository.findByEntityTypeAndEntityId(entityType, entityId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TimeTrackingDto update(Long id, TimeTrackingDto dto) {
        TimeTracking existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time Tracking record not found"));
        existing.setStartTime(dto.getStartTime());
        existing.setEndTime(dto.getEndTime());
        existing.setDurationMinutes(dto.getDurationMinutes());
        existing.setDescription(dto.getDescription());
        return mapper.toDto(repository.save(existing));
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "Time tracking entry deleted successfully";
    }
}

