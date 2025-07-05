package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectResourceAllocationDto;
import com.protasker.protasker_backend.model.ProjectModel.ProjectResourceAllocation;
import com.protasker.protasker_backend.repository.ProjectResourceAllocationRepository;
import com.protasker.protasker_backend.service.ProjectServices.ProjectResourceAllocationService;
import com.protasker.protasker_backend.utils.Mapper.ProjectMapper.ProjectResourceAllocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectResourceAllocationServiceImpl implements ProjectResourceAllocationService {

    private final ProjectResourceAllocationRepository repository;
    private final ProjectResourceAllocationMapper mapper;

    @Override
    public ProjectResourceAllocationDto create(ProjectResourceAllocationDto dto) {
        ProjectResourceAllocation saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public List<ProjectResourceAllocationDto> getByTeamMember(Long teamMemberId) {
        return repository.findByProjectTeamMemberId(teamMemberId)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "Allocation deleted successfully";
    }
}

