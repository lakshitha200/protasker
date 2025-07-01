package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
import com.protasker.protasker_backend.dto.ProjectDto.ProjectRequestDto;
import com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions.ProjectException;
import com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions.WorkspaceException;
import com.protasker.protasker_backend.exception.CusExceptions.UserNotFoundException;
import com.protasker.protasker_backend.model.Client;
import com.protasker.protasker_backend.model.ProjectModel.Project;
import com.protasker.protasker_backend.model.ProjectModel.Workspace;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.enums.ProjectEnums.Priority;
import com.protasker.protasker_backend.model.enums.ProjectEnums.ProjectStatus;
import com.protasker.protasker_backend.repository.*;
import com.protasker.protasker_backend.service.ProjectServices.ProjectService;
import com.protasker.protasker_backend.utils.ModelDtoConvertor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final ProjectBudgetRepository projectBudgetRepository;

    @Transactional
    @Override
    public GenericResponseDto createProject(ProjectRequestDto request) {
        // Validate and fetch dependencies
        Client client = null;
        if(request.getClientId()!=null){
            client = clientRepository.findByClientId(request.getClientId())
                    .orElseThrow(() -> new UserNotFoundException("Client not found"));
        }

        Workspace workspace = workspaceRepository.findByWorkspaceId(request.getWorkspaceId())
                .orElseThrow(() -> new WorkspaceException("Workspace not found"));

        User projectManager = userRepository.findByUserId(request.getProjectManagerId())
                .orElseThrow(() -> new UserNotFoundException("Project manager not found"));

        // Create project
        Project project = Project.builder()
                .projectId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(request.getStatus() != null ? request.getStatus() : ProjectStatus.DRAFT)
                .client(client)
                .workspace(workspace)
                .priority(request.getPriority() != null ? request.getPriority() : Priority.MEDIUM)
                .projectManager(projectManager)
                .projectType(request.getProjectType())
                .methodology(request.getMethodology())
                .build();
        Project savedProject = projectRepository.save(project);

        log.info("\u001B[32mCreated project ID: {}\u001B[0m", savedProject.getProjectId());
        return GenericResponseDto.builder()
                .response("Project created successfully")
                .code(HttpStatus.CREATED)
                .build();
    }

    @Override
    public ProjectDto getProject(String projectId) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ProjectException("Project not found with id: "+projectId));
        return ModelDtoConvertor.projectDtoConvertor(project);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(ModelDtoConvertor::projectDtoConvertor)
                .toList();
    }
}
