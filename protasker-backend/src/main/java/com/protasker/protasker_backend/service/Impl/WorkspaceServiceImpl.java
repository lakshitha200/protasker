package com.protasker.protasker_backend.service.Impl;

import com.protasker.protasker_backend.dto.WorkspaceDto.WorkspaceDto;
import com.protasker.protasker_backend.exception.CusExceptions.CustomNotFoundException;
import com.protasker.protasker_backend.exception.CusExceptions.UserNotFoundException;
import com.protasker.protasker_backend.model.Client;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.WorkspaceModel.Workspace;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUser;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUserId;
import com.protasker.protasker_backend.model.enums.WorkspaceRole;
import com.protasker.protasker_backend.repository.ClientRepository;
import com.protasker.protasker_backend.repository.UserRepository;
import com.protasker.protasker_backend.repository.WorkspaceRepository;
import com.protasker.protasker_backend.repository.WorkspaceUserRepository;
import com.protasker.protasker_backend.service.WorkspaceService;
import com.protasker.protasker_backend.utils.Mapper.WorkspaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final WorkspaceMapper mapper;
    private final WorkspaceUserRepository workspaceUserRepository;

    @Transactional
    @Override
    public WorkspaceDto createWorkspace(WorkspaceDto workspaceDto, String createdByUserId) {
        User createdBy = userRepository.findByUserId(createdByUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + createdByUserId));

        Client client = null;
        if (workspaceDto.getClientId() != null) {
            client = clientRepository.findById(workspaceDto.getClientId())
                    .orElse(null); // Optional: ignore if client not found
        }

        // Generate UUID workspaceId if missing
        if (workspaceDto.getWorkspaceId() == null || workspaceDto.getWorkspaceId().isEmpty()) {
            workspaceDto.setWorkspaceId(UUID.randomUUID().toString());
        }

        // Check slug uniqueness
        if (workspaceRepository.existsBySlug(workspaceDto.getSlug())) {
            throw new IllegalArgumentException("Slug already exists");
        }
        if (workspaceDto.getSlug() == null || workspaceDto.getSlug().isBlank()) {
            workspaceDto.setSlug(generateSlug(workspaceDto.getName()));
        }
        Workspace workspace = mapper.toEntity(workspaceDto, createdBy, client);

        Workspace saved = workspaceRepository.save(workspace);

        // Add creator as workspace user with role
        WorkspaceUser wu = WorkspaceUser.builder()
                .id(new WorkspaceUserId(workspace.getId(), createdBy.getId()))
                .workspace(workspace)
                .user(createdBy)
                .role(WorkspaceRole.SUPER_ADMIN)
                .build();

        workspaceUserRepository.save(wu);

        return mapper.toDto(saved);
    }

    @Transactional
    @Override
    public WorkspaceDto updateWorkspace(String workspaceId, WorkspaceDto workspaceDto) {
        Workspace existing = workspaceRepository.findByWorkspaceId(workspaceId)
                .orElseThrow(() -> new CustomNotFoundException("Workspace not found with id: " + workspaceId));

        existing.setName(workspaceDto.getName());
        existing.setSlug(workspaceDto.getSlug());
        existing.setType(workspaceDto.getType());
        existing.setDescription(workspaceDto.getDescription());
        existing.setLogoUrl(workspaceDto.getLogoUrl());

        if (workspaceDto.getClientId() != null) {
            Client client = clientRepository.findById(workspaceDto.getClientId())
                    .orElse(null);
            existing.setClient(client);
        } else {
            existing.setClient(null);
        }

        Workspace updated = workspaceRepository.save(existing);
        return mapper.toDto(updated);
    }

    @Transactional(readOnly = true)
    @Override
    public WorkspaceDto getWorkspaceById(String workspaceId) {
        Workspace workspace = workspaceRepository.findByWorkspaceId(workspaceId)
                .orElseThrow(() -> new CustomNotFoundException("Workspace not found with id: " + workspaceId));
        return mapper.toDto(workspace);
    }

    @Transactional(readOnly = true)
    @Override
    public List<WorkspaceDto> getAllWorkspaces() {
        return workspaceRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteWorkspace(String workspaceId) {
        Workspace workspace = workspaceRepository.findByWorkspaceId(workspaceId)
                .orElseThrow(() -> new CustomNotFoundException("Workspace not found with id: " + workspaceId));
        workspaceRepository.delete(workspace);
    }

    private String generateSlug(String name) {
        String slug = name.toLowerCase().replaceAll("[^a-z0-9\\-]+", "-");
        String baseSlug = slug;
        int counter = 1;

//        while (workspaceRepository.existsBySlug(slug)) {
//            slug = baseSlug + "-" + counter;
//            counter++;
//        }

        return slug;
    }
}

