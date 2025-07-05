//package com.protasker.protasker_backend.utils;
//
//import com.protasker.protasker_backend.dto.ProjectDto.ProjectDto;
//import com.protasker.protasker_backend.dto.ProjectDto.WorkspaceDto;
//import com.protasker.protasker_backend.dto.UserDto.UserDto;
//import com.protasker.protasker_backend.model.ProjectModel.Project;
//import com.protasker.protasker_backend.model.WorkspaceModel.Workspace;
//import com.protasker.protasker_backend.model.User;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ModelDtoConvertor {
//
//    public static WorkspaceDto workspaceDtoConvertor(Workspace workspace) {
//        return WorkspaceDto.builder()
//                .id(workspace.getId())
//                .workspaceId(workspace.getWorkspaceId())
//                .name(workspace.getName())
//                .slug(workspace.getSlug())
//                .type(workspace.getType())
//                .createdBy(userDtoConvertor(workspace.getCreatedBy()).toString())
//                .clientId(workspace.getClient().getClientId())
//                .description(workspace.getDescription())
//                .logoUrl(workspace.getLogoUrl())
//                .createdAt(workspace.getCreatedAt())
//                .build();
//    }
//
//    public static UserDto userDtoConvertor(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .userId(user.getUserId())
//                .email(user.getEmail())
//                .username(user.getUsername())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .department(user.getDepartment())
//                .position(user.getPosition())
//                .profilePicture(user.getProfilePicture())
//                .phoneNumber(user.getPhoneNumber())
//                .skills(user.getSkills())
//                .isActive(user.getIsActive())
//                .userType(user.getUserType())
//                .lastLogin(user.getLastLogin())
//                .createdAt(user.getCreatedAt())
//                .build();
//    }
//
//    public static ProjectDto projectDtoConvertor(Project project) {
//        return ProjectDto.builder()
//                .id(project.getId())
//                .projectId(project.getProjectId())
//                .name(project.getName())
//                .description(project.getDescription())
//                .startDate(project.getStartDate())
//                .endDate(project.getEndDate())
//                .status(project.getStatus())
//                .clientId(project.getClient().getId())
//                .workspaceId(workspaceDtoConvertor(project.getWorkspace()))
//                .priority(project.getPriority())
//                .projectManager(userDtoConvertor(project.getProjectManager()))
//                .projectType(project.getProjectType())
//                .methodology(project.getMethodology())
//                .createdAt(project.getCreatedAt())
//                .updatedAt(project.getUpdatedAt())
//                .build();
//    }
//}
