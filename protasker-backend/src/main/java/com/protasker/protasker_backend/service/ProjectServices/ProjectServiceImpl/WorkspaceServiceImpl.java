package com.protasker.protasker_backend.service.ProjectServices.ProjectServiceImpl;

import com.protasker.protasker_backend.constants.WorkspaceConstants;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceDto;
import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceRequestDto;
import com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions.WorkspaceException;
import com.protasker.protasker_backend.exception.CusExceptions.UserNotFoundException;
import com.protasker.protasker_backend.model.Client;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.Workspace;
import com.protasker.protasker_backend.repository.ClientRepository;
import com.protasker.protasker_backend.repository.UserRepository;
import com.protasker.protasker_backend.repository.WorkspaceRepository;
import com.protasker.protasker_backend.service.ProjectServices.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
//@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public GenericResponseDto createWorkspace(WorkspaceRequestDto workspaceRequestDto, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("Workspace Service: User not found with user id: "+userId));

        Client client = null;
        if (workspaceRequestDto.getClientId() != null) {
            client = clientRepository.findByClientId(workspaceRequestDto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Workspace Service: Client not found"));
        }

        try{
            Workspace workspace = Workspace.builder()
                    .workspaceId(UUID.randomUUID().toString())
                    .name(workspaceRequestDto.getName())
                    .slug(generateSlug(workspaceRequestDto.getName()))
                    .type(workspaceRequestDto.getType())
                    .createdBy(user)
                    .client(client)
                    .description(workspaceRequestDto.getDescription())
                    .logoUrl(workspaceRequestDto.getLogo_url())
                    .build();

            workspaceRepository.save(workspace);
        }catch (Exception e){
            throw new WorkspaceException("Workspace Service: "+WorkspaceConstants.WORKSPACE_CREATION_FAIL.getMessage(),e);
        }

        return GenericResponseDto.builder()
                .response(WorkspaceConstants.WORKSPACE_CREATED.getMessage())
                .code(HttpStatus.CREATED)
                .build();
    }

    @Override
    public WorkspaceDto getWorkspace(String workspaceId) {
        Workspace workspace = workspaceRepository.findByWorkspaceId(workspaceId)
                .orElseThrow(() -> new WorkspaceException("Workspace Service: "+WorkspaceConstants.WORKSPACE_NOT_FOUND.getMessage()));

        return WorkspaceDto.builder()
                .id(workspace.getId())
                .workspaceId(workspace.getWorkspaceId())
                .name(workspace.getName())
                .slug(workspace.getSlug())
                .type(workspace.getType())
                .createdBy(workspace.getCreatedBy().getUserId())
                .clientId(workspace.getClient().getClientId())
                .description(workspace.getDescription())
                .logoUrl(workspace.getLogoUrl())
                .createdAt(workspace.getCreatedAt())
                .updatedAt(workspace.getUpdatedAt())
                .build();
//        return convertToDto(workspace);
    }
//
//    public List<WorkspaceDTO> getUserWorkspaces(String userId) {
//        List<WorkspaceUser> memberships = workspaceUserRepository.findByUserId(userId);
//        return memberships.stream()
//                .map(membership -> convertToDto(membership.getWorkspace()))
//                .collect(Collectors.toList());
//    }
//
//    public WorkspaceDTO updateWorkspace(String workspaceId, WorkspaceRequest request) {
//        Workspace workspace = workspaceRepository.findByWorkspaceId(workspaceId)
//                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
//
//        if (request.getName() != null) {
//            workspace.setName(request.getName());
//            workspace.setSlug(generateSlug(request.getName()));
//        }
//        if (request.getDescription() != null) {
//            workspace.setDescription(request.getDescription());
//        }
//        if (request.getType() != null) {
//            workspace.setType(request.getType());
//        }
//
//        workspace = workspaceRepository.save(workspace);
//        return convertToDto(workspace);
//    }
//
//    public void deleteWorkspace(String workspaceId) {
//        Workspace workspace = workspaceRepository.findByWorkspaceId(workspaceId)
//                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
//        workspaceRepository.delete(workspace);
//    }
//
//    public WorkspaceMemberDTO addMember(String workspaceId, String userId, String role) {
//        Workspace workspace = workspaceRepository.findByWorkspaceId(workspaceId)
//                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        if (workspaceUserRepository.existsByWorkspaceIdAndUserId(workspace.getId(), user.getId())) {
//            throw new BusinessException("User is already a member of this workspace");
//        }
//
//        WorkspaceUser membership = WorkspaceUser.builder()
//                .id(new WorkspaceUserId(workspace.getId(), user.getId()))
//                .workspace(workspace)
//                .user(user)
//                .role(role)
//                .build();
//
//        workspaceUserRepository.save(membership);
//        return convertToMemberDto(membership);
//    }
//
//    public void removeMember(String workspaceId, String userId) {
//        Workspace workspace = workspaceRepository.findByWorkspaceId(workspaceId)
//                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        workspaceUserRepository.deleteById(new WorkspaceUserId(workspace.getId(), user.getId()));
//    }
//
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

//    private WorkspaceDTO convertToDto(Workspace workspace) {
//        WorkspaceDTO dto = modelMapper.map(workspace, WorkspaceDTO.class);
//        dto.setCreatedByUserId(workspace.getCreatedBy().getId().toString());
//        if (workspace.getClient() != null) {
//            dto.setClientId(workspace.getClient().getClientId());
//        }
//        return dto;
//    }
//
//    private WorkspaceMemberDTO convertToMemberDto(WorkspaceUser membership) {
//        return modelMapper.map(membership, WorkspaceMemberDTO.class);
//    }
}
