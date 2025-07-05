package com.protasker.protasker_backend.utils.CustomAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkspacePermission {
    String[] allowedRoles();
    String workspaceIdParam(); // e.g. "workspaceId"
}
