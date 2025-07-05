package com.protasker.protasker_backend.cache;

import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import com.protasker.protasker_backend.repository.ProjectTeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionCacheService {

    private final ProjectTeamMemberRepository teamMemberRepo;

    @Cacheable(value = "permissions", key = "#teamMemberId")
    public Set<String> getPermissions(Long teamMemberId) {
        ProjectTeamMember teamMember = teamMemberRepo.findById(teamMemberId)
                .orElseThrow(() -> new RuntimeException("Team member not found"));

        return teamMember.getRole().getPermissions().stream()
                .map(p -> p.getCode().toLowerCase())
                .collect(Collectors.toSet());
    }
}

