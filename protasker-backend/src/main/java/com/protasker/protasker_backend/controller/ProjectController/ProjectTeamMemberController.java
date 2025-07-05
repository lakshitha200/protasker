package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectTeamMemberDto;
import com.protasker.protasker_backend.service.ProjectServices.ProjectTeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/team")
@RequiredArgsConstructor
public class ProjectTeamMemberController {

    private final ProjectTeamMemberService service;

    @PostMapping("/{projectId}/assign")
    public ResponseEntity<ProjectTeamMemberDto> assign(
            @PathVariable String projectId,
            @RequestBody ProjectTeamMemberDto dto
    ) {
        return ResponseEntity.ok(service.assign(projectId, dto));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<ProjectTeamMemberDto>> getTeam(@PathVariable String projectId) {
        return ResponseEntity.ok(service.getTeamByProject(projectId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.remove(id));
    }
}

