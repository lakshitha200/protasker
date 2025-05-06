package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceDto;
import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceRequestDto;
import com.protasker.protasker_backend.service.ProjectServices.WorkspaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<GenericResponseDto> createWorkspace(
            @Valid @RequestBody WorkspaceRequestDto workspaceRequestDto,
            @RequestHeader("X-User-Id") String userId) {
        return new ResponseEntity<>(workspaceService.createWorkspace(workspaceRequestDto, userId), HttpStatus.CREATED);
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDto> getWorkspace(@PathVariable String workspaceId) {
        return new ResponseEntity<>(workspaceService.getWorkspace(workspaceId),HttpStatus.OK);
    }
//
//    @GetMapping
//    public ResponseEntity<List<WorkspaceDTO>> getUserWorkspaces(@RequestHeader("X-User-Id") String userId) {
//        List<WorkspaceDTO> workspaces = workspaceService.getUserWorkspaces(userId);
//        return ResponseEntity.ok(workspaces);
//    }
//
//    @PutMapping("/{workspaceId}")
//    public ResponseEntity<WorkspaceDTO> updateWorkspace(
//            @PathVariable String workspaceId,
//            @Valid @RequestBody WorkspaceRequest request) {
//        WorkspaceDTO workspace = workspaceService.updateWorkspace(workspaceId, request);
//        return ResponseEntity.ok(workspace);
//    }
//
//    @DeleteMapping("/{workspaceId}")
//    public ResponseEntity<Void> deleteWorkspace(@PathVariable String workspaceId) {
//        workspaceService.deleteWorkspace(workspaceId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PostMapping("/{workspaceId}/members")
//    public ResponseEntity<WorkspaceMemberDTO> addMember(
//            @PathVariable String workspaceId,
//            @RequestParam String userId,
//            @RequestParam String role) {
//        WorkspaceMemberDTO member = workspaceService.addMember(workspaceId, userId, role);
//        return ResponseEntity.status(HttpStatus.CREATED).body(member);
//    }
//
//    @DeleteMapping("/{workspaceId}/members/{userId}")
//    public ResponseEntity<Void> removeMember(
//            @PathVariable String workspaceId,
//            @PathVariable String userId) {
//        workspaceService.removeMember(workspaceId, userId);
//        return ResponseEntity.noContent().build();
//    }
}
