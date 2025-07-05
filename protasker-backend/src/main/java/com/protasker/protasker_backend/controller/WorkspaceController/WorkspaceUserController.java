package com.protasker.protasker_backend.controller.WorkspaceController;

import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceUserDto;
import com.protasker.protasker_backend.service.WorkspaceUserService;
import com.protasker.protasker_backend.utils.CustomAnnotation.WorkspacePermission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// WorkspaceUserController.java
@RestController
@RequestMapping("/api/workspaces/users")
@RequiredArgsConstructor
public class WorkspaceUserController {

    private final WorkspaceUserService service;

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN"})
    @PostMapping
    public ResponseEntity<WorkspaceUserDto> assign(@RequestBody WorkspaceUserDto dto) {
        return ResponseEntity.ok(service.assignRole(dto));
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN","PROJECT_MANAGER","TEAM_MEMBER"})
    @GetMapping("/{workspaceId}/members")
    public ResponseEntity<List<WorkspaceUserDto>> getByWorkspace(@PathVariable Long workspaceId) {
        return ResponseEntity.ok(service.getUsersByWorkspace(workspaceId));
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN","PROJECT_MANAGER","TEAM_MEMBER"})
    @GetMapping("/{workspaceId}/{userId}")
    public ResponseEntity<WorkspaceUserDto> getUser(
            @PathVariable Long workspaceId,
            @PathVariable Long userId) {
        return ResponseEntity.ok(service.getById(workspaceId, userId));
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN"})
    @PutMapping("/{workspaceId}/{userId}/role")
    public ResponseEntity<WorkspaceUserDto> updateRole(
            @PathVariable Long workspaceId,
            @PathVariable Long userId,
            @RequestParam String newRole) {
        return ResponseEntity.ok(service.updateRole(workspaceId, userId, newRole));
    }

    @WorkspacePermission(workspaceIdParam = "workspaceId", allowedRoles = {"SUPER_ADMIN", "ADMIN"})
    @DeleteMapping("/{workspaceId}/{userId}")
    public ResponseEntity<String> removeUser(
            @PathVariable Long workspaceId,
            @PathVariable Long userId) {
        return ResponseEntity.ok(service.removeUser(workspaceId, userId));
    }
}

