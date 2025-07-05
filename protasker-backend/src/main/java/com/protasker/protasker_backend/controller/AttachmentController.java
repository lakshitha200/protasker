package com.protasker.protasker_backend.controller;

import com.protasker.protasker_backend.dto.AttachmentDto;
import com.protasker.protasker_backend.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService service;

    @PostMapping
    public ResponseEntity<AttachmentDto> upload(@RequestBody AttachmentDto dto) {
        return ResponseEntity.ok(service.upload(dto));
    }

    @GetMapping("/{entityType}/{entityId}")
    public ResponseEntity<List<AttachmentDto>> getByEntity(
            @PathVariable String entityType,
            @PathVariable Long entityId) {
        return ResponseEntity.ok(service.getByEntity(entityType, entityId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

