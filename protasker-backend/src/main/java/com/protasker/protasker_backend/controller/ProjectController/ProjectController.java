package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
import com.protasker.protasker_backend.dto.ProjectDto.ProjectRequestDto;
import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceDto;
import com.protasker.protasker_backend.service.ProjectServices.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<GenericResponseDto> createProject(@RequestBody @Valid ProjectRequestDto request) {
        return new ResponseEntity<>(projectService.createProject(request), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable String projectId) {
        return new ResponseEntity<>(projectService.getProject(projectId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
}
