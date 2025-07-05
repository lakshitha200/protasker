package com.protasker.protasker_backend.service.Impl;

import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceUserDto;
import com.protasker.protasker_backend.exception.CusExceptions.CustomNotFoundException;
import com.protasker.protasker_backend.exception.CusExceptions.UserNotFoundException;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.WorkspaceModel.Workspace;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUser;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUserId;
import com.protasker.protasker_backend.model.enums.WorkspaceRole;
import com.protasker.protasker_backend.repository.UserRepository;
import com.protasker.protasker_backend.repository.WorkspaceRepository;
import com.protasker.protasker_backend.repository.WorkspaceUserRepository;
import com.protasker.protasker_backend.service.WorkspaceUserService;
import com.protasker.protasker_backend.utils.Mapper.WorkspaceUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// WorkspaceUserServiceImpl.java
@Service
@RequiredArgsConstructor
public class WorkspaceUserServiceImpl implements WorkspaceUserService {

    private final WorkspaceUserRepository repository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceUserMapper mapper;

    @Transactional
    @Override
    public WorkspaceUserDto assignRole(WorkspaceUserDto dto) {
        Workspace workspace = workspaceRepository.findById(dto.getWorkspaceId())
                .orElseThrow(() -> new CustomNotFoundException("Workspace not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (repository.existsByWorkspaceIdAndUserId(dto.getWorkspaceId(), dto.getUserId())) {
            throw new IllegalStateException("User is already in the workspace.");
        }

        WorkspaceUser entity = mapper.toEntity(workspace, user, dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<WorkspaceUserDto> getUsersByWorkspace(Long workspaceId) {
        return repository.findByWorkspaceId(workspaceId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public WorkspaceUserDto getById(Long workspaceId, Long userId) {
        WorkspaceUser entity = repository.findById(new WorkspaceUserId(workspaceId, userId))
                .orElseThrow(() -> new RuntimeException("User not found in workspace"));
        return mapper.toDto(entity);
    }

    @Override
    public WorkspaceUserDto updateRole(Long workspaceId, Long userId, String newRole) {
        WorkspaceUser entity = repository.findById(new WorkspaceUserId(workspaceId, userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        entity.setRole(WorkspaceRole.valueOf(newRole));
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public String removeUser(Long workspaceId, Long userId) {
        WorkspaceUserId id = new WorkspaceUserId(workspaceId, userId);
        if (!repository.existsById(id)) throw new RuntimeException("User not found");
        repository.deleteById(id);
        return "User removed from workspace";
    }
}

