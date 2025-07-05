package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;

import com.protasker.protasker_backend.dto.ProjectDto.MeetingScheduleDto;
import com.protasker.protasker_backend.model.ProjectModel.MeetingSchedule;
import com.protasker.protasker_backend.repository.MeetingScheduleRepository;
import com.protasker.protasker_backend.service.ProjectServices.MeetingScheduleService;
import com.protasker.protasker_backend.utils.Mapper.ProjectMapper.MeetingScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingScheduleServiceImpl implements MeetingScheduleService {

    private final MeetingScheduleRepository repository;
    private final MeetingScheduleMapper mapper;

    @Override
    public MeetingScheduleDto create(MeetingScheduleDto dto) {
        MeetingSchedule saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public List<MeetingScheduleDto> getByProject(Long projectId) {
        return repository.findByProjectId(projectId)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public String cancel(Long id) {
        MeetingSchedule meeting = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        meeting.setStatus("CANCELLED");
        repository.save(meeting);
        return "Meeting cancelled";
    }
}

