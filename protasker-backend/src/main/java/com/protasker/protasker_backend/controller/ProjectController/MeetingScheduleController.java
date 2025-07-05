package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.ProjectDto.MeetingScheduleDto;
import com.protasker.protasker_backend.service.ProjectServices.MeetingScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingScheduleController {

    private final MeetingScheduleService service;

    @PostMapping
    public ResponseEntity<MeetingScheduleDto> create(@RequestBody MeetingScheduleDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MeetingScheduleDto>> getByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.getByProject(projectId));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelMeeting(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancel(id));
    }
}

