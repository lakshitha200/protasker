package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectTeamMemberDto;
import com.protasker.protasker_backend.exception.CusExceptions.CustomNotFoundException;
import com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions.ProjectNotFoundException;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import com.protasker.protasker_backend.repository.ProjectRepository;
import com.protasker.protasker_backend.repository.ProjectTeamMemberRepository;
import com.protasker.protasker_backend.service.ProjectServices.ProjectTeamMemberService;
import com.protasker.protasker_backend.utils.Mapper.ProjectMapper.ProjectTeamMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTeamMemberServiceImpl implements ProjectTeamMemberService {

    private final ProjectTeamMemberRepository teamMemberRepo;
    private final ProjectRepository projectRepo;
    private final ProjectTeamMemberMapper mapper;

    @Transactional
    @Override
    public ProjectTeamMemberDto assign(String projectId, ProjectTeamMemberDto dto) {
        Project project = projectRepo.findByProjectId(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + projectId));
        dto.setProjectId(project.getId());

        boolean exists = teamMemberRepo.existsByProjectIdAndUserIdAndRoleId(
                dto.getProjectId(), dto.getUserId(), dto.getRoleId()
        );
        if (exists) throw new IllegalStateException("User already assigned with this role");

        ProjectTeamMember saved = teamMemberRepo.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Transactional
    @Override
    public List<ProjectTeamMemberDto> getTeamByProject(String projectId) {
        Project project = projectRepo.findByProjectId(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + projectId));
        System.out.println("Works");
        return teamMemberRepo.findByProjectId(project.getId())
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String remove(Long id) {
        teamMemberRepo.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Team member not found with id: " + id));
        teamMemberRepo.deleteById(id);
        return "Team member removed successfully";
    }
}

