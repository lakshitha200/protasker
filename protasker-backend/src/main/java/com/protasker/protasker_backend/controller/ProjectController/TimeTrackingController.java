package com.protasker.protasker_backend.controller.ProjectController;


import com.protasker.protasker_backend.dto.ProjectDto.TimeTrackingDto;
import com.protasker.protasker_backend.service.ProjectServices.TimeTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-tracking")
@RequiredArgsConstructor
public class TimeTrackingController {

    private final TimeTrackingService service;

    @PostMapping
    public ResponseEntity<TimeTrackingDto> create(@RequestBody TimeTrackingDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{entityType}/{entityId}")
    public ResponseEntity<List<TimeTrackingDto>> getByEntity(
            @PathVariable String entityType,
            @PathVariable Long entityId) {
        return ResponseEntity.ok(service.getByEntity(entityType, entityId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeTrackingDto> update(@PathVariable Long id, @RequestBody TimeTrackingDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

