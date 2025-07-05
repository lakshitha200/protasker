package com.protasker.protasker_backend.controller.WorkspaceController;

import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceDto;
import com.protasker.protasker_backend.service.WorkspaceService;
import com.protasker.protasker_backend.utils.CustomAnnotation.WorkspacePermission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<WorkspaceDto> createWorkspace(
            @RequestBody WorkspaceDto workspaceDto,
            @RequestHeader("X-User-Id") String createdByUserId
    ) {
        WorkspaceDto created = workspaceService.createWorkspace(workspaceDto, createdByUserId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN"})
    @PutMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDto> updateWorkspace(
            @PathVariable String workspaceId,
            @RequestBody WorkspaceDto workspaceDto
    ) {
        WorkspaceDto updated = workspaceService.updateWorkspace(workspaceId, workspaceDto);
        return ResponseEntity.ok(updated);
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN","PROJECT_MANAGER","TEAM_MEMBER"})
    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDto> getWorkspace(@PathVariable String workspaceId) {
        WorkspaceDto dto = workspaceService.getWorkspaceById(workspaceId);
        return ResponseEntity.ok(dto);
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN","PROJECT_MANAGER","TEAM_MEMBER"})
    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> getAllWorkspaces() {
        List<WorkspaceDto> list = workspaceService.getAllWorkspaces();
        return ResponseEntity.ok(list);
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN"})
    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable String workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }
}
