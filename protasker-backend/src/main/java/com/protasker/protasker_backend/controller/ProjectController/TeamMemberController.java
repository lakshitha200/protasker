package com.protasker.protasker_backend.controller.ProjectController;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.ProjectDto.*;
import com.protasker.protasker_backend.service.ProjectServices.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
import com.protasker.protasker_backend.dto.ProjectDto.ProjectRequestDto;
import com.protasker.protasker_backend.service.ProjectServices.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/projects/team")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @PostMapping
    public ResponseEntity<GenericResponseDto> addTeamMembersToProject(@RequestBody @Valid TeamMemberRequestDto request) {
        return new ResponseEntity<>(teamMemberService.addTeamMembersToProject(request), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<TeamMemberDto> getTeamMember(@PathVariable String userId) {
        return new ResponseEntity<>(teamMemberService.getTeamMember(userId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeamMemberDto>> getAllProjects() {
        List<ProjectDto> projects = teamMemberService.getAllTeamMembers();
        return ResponseEntity.ok(projects);
    }
}
