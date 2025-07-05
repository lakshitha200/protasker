package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.ProjectDto.ProjectExpenseDto;
import com.protasker.protasker_backend.service.ProjectServices.ProjectExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/expenses")
@RequiredArgsConstructor
public class ProjectExpenseController {

    private final ProjectExpenseService service;

    @PostMapping
    public ResponseEntity<ProjectExpenseDto> create(@PathVariable String projectId, @RequestBody ProjectExpenseDto dto) {
        return new ResponseEntity<>(service.create(projectId,dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectExpenseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectExpenseDto>> getAll(@PathVariable String projectId) {
        return ResponseEntity.ok(service.getAllByProject(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectExpenseDto> update(@PathVariable Long id, @RequestBody ProjectExpenseDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
