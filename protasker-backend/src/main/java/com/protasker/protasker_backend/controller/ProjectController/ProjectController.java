package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
import com.protasker.protasker_backend.service.ProjectServices.ProjectService;
import com.protasker.protasker_backend.utils.CustomAnnotation.RequirePermission;
import com.protasker.protasker_backend.utils.CustomAnnotation.WorkspacePermission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN","PROJECT_MANAGER"})
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto dto) {
        return ResponseEntity.ok(projectService.createProject(dto));
    }

    @RequirePermission("project:read:any")
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getById(@PathVariable String projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @RequirePermission("project:read:any")
    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<ProjectDto>> getByWorkspace(@PathVariable Long workspaceId) {
        return ResponseEntity.ok(projectService.getProjectsByWorkspaceId(workspaceId));
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN","PROJECT_MANAGER"})
    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> update(@PathVariable String projectId, @RequestBody ProjectDto dto) {
        return ResponseEntity.ok(projectService.updateProject(projectId, dto));
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN","PROJECT_MANAGER"})
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> delete(@PathVariable String projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}