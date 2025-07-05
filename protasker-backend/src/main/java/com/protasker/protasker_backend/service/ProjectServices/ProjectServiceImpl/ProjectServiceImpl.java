package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
import com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions.ProjectNotFoundException;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.repository.ProjectRepository;
import com.protasker.protasker_backend.service.ProjectServices.ProjectService;
import com.protasker.protasker_backend.utils.Mapper.ProjectMapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto createProject(ProjectDto dto) {
        Project entity = projectMapper.toEntity(dto);
        entity.setProjectId(UUID.randomUUID().toString());
        Project saved = projectRepository.save(entity);
        return projectMapper.toDto(saved);
    }

    @Override
    public ProjectDto getProjectById(String projectId) {
        return projectRepository.findByProjectId(projectId)
                .map(projectMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public List<ProjectDto> getProjectsByWorkspaceId(Long workspaceId) {
        return projectRepository.findByWorkspaceId(workspaceId).stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProject(String projectId) {
        Project entity = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.delete(entity);
    }

    @Override
    public ProjectDto updateProject(String projectId, ProjectDto dto) {
        Project existing = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setStatus(dto.getStatus());
        existing.setPriority(dto.getPriority());
        existing.setProjectType(dto.getProjectType());
        existing.setMethodology(dto.getMethodology());

        Project updated = projectRepository.save(existing);
        return projectMapper.toDto(updated);
    }
}