package com.protasker.protasker_backend.constants;

import lombok.Getter;

@Getter
public enum WorkspaceConstants {

    WORKSPACE_CREATED("Workspace created successfully"),
    WORKSPACE_UPDATED("Workspace updated successfully"),
    WORKSPACE_DELETED("Workspace deleted successfully"),
    WORKSPACE_MEMBER_ADDED("Member added to workspace"),
    WORKSPACE_MEMBER_REMOVED("Member removed from workspace"),
    WORKSPACE_ROLE_UPDATED("Member role updated"),
    WORKSPACE_INVITATION_SENT("Invitation sent successfully"),
    WORKSPACE_SETTINGS_UPDATED("Workspace settings saved"),
    WORKSPACE_LOGO_UPDATED("Workspace logo updated"),
    WORKSPACE_ARCHIVED("Workspace archived"),
    WORKSPACE_RESTORED("Workspace restored from archive"),
    WORKSPACE_EXPORTED("Workspace data exported"),
    WORKSPACE_BRANDING_UPDATED("Branding updated successfully"),

    // Error Messages
    WORKSPACE_CREATION_FAIL("Workspace creation fail"),
    WORKSPACE_NOT_FOUND("Workspace not found"),
    WORKSPACE_ACCESS_DENIED("You don't have permission for this workspace"),
    WORKSPACE_NAME_EXISTS("Workspace name already in use"),
    WORKSPACE_SLUG_EXISTS("Workspace URL slug already taken"),
    WORKSPACE_LIMIT_REACHED("Maximum workspaces limit reached"),
    WORKSPACE_MEMBER_EXISTS("User is already a member"),
    WORKSPACE_MEMBER_LIMIT("Workspace member limit reached"),
    WORKSPACE_INVALID_TYPE("Invalid workspace type"),
    WORKSPACE_NOT_ACTIVE("Workspace is inactive or suspended"),
    WORKSPACE_DELETE_RESTRICTED("Workspace cannot be deleted (contains active projects)"),
    WORKSPACE_INVITATION_FAILED("Failed to send invitation"),
    WORKSPACE_LOGO_UPLOAD_FAILED("Logo upload failed"),
    WORKSPACE_EXPORT_FAILED("Failed to export workspace data"),

    // Invitation Messages
    INVITATION_ACCEPTED("Workspace invitation accepted"),
    INVITATION_DECLINED("Workspace invitation declined"),
    INVITATION_EXPIRED("Invitation link expired"),
    INVITATION_INVALID("Invalid invitation token"),
    INVITATION_REVOKED("Invitation revoked by admin");

//    PROJECT_CREATED("Project created successfully"),
//    PROJECT_MOVED("Project moved to another workspace"),
//    PROJECT_DELETED("Project deleted"),
//    PROJECT_ARCHIVED("Project archived"),
//    PROJECT_RESTORED("Project restored from archive"),
//    PROJECT_TEMPLATE_APPLIED("Project template applied successfully");

    private final String message;

    WorkspaceConstants( String message) {
        this.message = message;
    }
}
