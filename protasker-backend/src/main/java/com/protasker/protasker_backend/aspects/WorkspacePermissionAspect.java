package com.protasker.protasker_backend.aspects;

import com.protasker.protasker_backend.exception.CusExceptions.CustomNotFoundException;
import com.protasker.protasker_backend.model.WorkspaceModel.Workspace;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUser;
import com.protasker.protasker_backend.model.WorkspaceModel.WorkspaceUserId;
import com.protasker.protasker_backend.repository.WorkspaceRepository;
import com.protasker.protasker_backend.repository.WorkspaceUserRepository;
import com.protasker.protasker_backend.utils.CustomAnnotation.WorkspacePermission;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class WorkspacePermissionAspect {

    private final WorkspaceUserRepository workspaceUserRepository;
    private final WorkspaceRepository workspaceRepository;
    private final HttpServletRequest request;

    @Around("@annotation(permission)")
    public Object checkWorkspacePermission(ProceedingJoinPoint joinPoint,
                                           WorkspacePermission permission) throws Throwable {

        // 1. Get userId from header
        String userIdHeader = request.getHeader("X-User-Id");
        if (userIdHeader == null) {
            throw new AccessDeniedException("Missing X-User-Id header");
        }
        Long userId = Long.parseLong(userIdHeader);
        System.out.println(userId);
        // 2. Extract workspaceId (UUID) from controller method param
        String workspaceIdHeader = request.getHeader("X-Workspace-Id");
        String workspaceUuid = null;
        if (workspaceIdHeader != null && !workspaceIdHeader.isEmpty()) {
            workspaceUuid =  workspaceIdHeader;
        }else {
             workspaceUuid = extractWorkspaceUuid(joinPoint, permission.workspaceIdParam());
        }
        System.out.println(workspaceUuid);
        // 3. Convert UUID to internal DB ID
        Workspace workspace = workspaceRepository.findByWorkspaceId(workspaceUuid)
                .orElseThrow(() -> new CustomNotFoundException("Workspace not found"));

        Long workspaceDbId = workspace.getId();
        System.out.println(workspaceDbId);

        // 4. Check WorkspaceUser table for role
        WorkspaceUserId workspaceUserId = new WorkspaceUserId(workspaceDbId, userId);
        System.out.println(workspaceUserId);
        WorkspaceUser wu = workspaceUserRepository.findById(workspaceUserId)
                .orElseThrow(() -> new CustomNotFoundException("User not in this workspace"));
        System.out.println("Wu:"+wu);
        if (!Arrays.asList(permission.allowedRoles()).contains(wu.getRole().name())) {
            throw new AccessDeniedException("User lacks required role, Wu:" + wu);
        }


        return joinPoint.proceed();
    }

    private String extractWorkspaceUuid(ProceedingJoinPoint joinPoint, String paramName) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            if (paramName.equals(paramNames[i]) && args[i] instanceof String) {
                return (String) args[i];
            }
        }

        throw new IllegalArgumentException("Missing workspaceId parameter: " + paramName);
    }
}