package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectResourceAllocationDto;
import com.protasker.protasker_backend.service.ProjectServices.ProjectResourceAllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/allocations")
@RequiredArgsConstructor
public class ProjectResourceAllocationController {

    private final ProjectResourceAllocationService service;

    @PostMapping
    public ResponseEntity<ProjectResourceAllocationDto> create(@RequestBody ProjectResourceAllocationDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/team-member/{id}")
    public ResponseEntity<List<ProjectResourceAllocationDto>> getByTeamMember(@PathVariable Long id) {
        return ResponseEntity.ok(service.getByTeamMember(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

