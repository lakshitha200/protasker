package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectBudgetDto;
import com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl.ProjectBudgetServiceImpl;
import com.protasker.protasker_backend.utils.CustomAnnotation.RequirePermission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/budgets")
@RequiredArgsConstructor
public class ProjectBudgetController {

    private final ProjectBudgetServiceImpl service;

    @PostMapping
    public ResponseEntity<ProjectBudgetDto> create(@PathVariable String projectId, @RequestBody ProjectBudgetDto dto) {
        return new ResponseEntity<>(service.create(projectId,dto), HttpStatus.CREATED);
    }

    @RequirePermission("project:update:own")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectBudgetDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectBudgetDto>> getAll(@PathVariable String projectId) {
        return ResponseEntity.ok(service.getAllByProject(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectBudgetDto> update(@PathVariable Long id, @RequestBody ProjectBudgetDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
