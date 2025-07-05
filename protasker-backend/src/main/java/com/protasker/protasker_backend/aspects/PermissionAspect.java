package com.protasker.protasker_backend.aspects;

import com.protasker.protasker_backend.security.PermissionChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class PermissionAspect {

    private final PermissionChecker checker;
    private final PermissionContextResolver resolver;

    @Around("@annotation(com.protasker.protasker_backend.utils.CustomAnnotation.RequirePermission)")
    public Object intercept(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        com.protasker.protasker_backend.utils.CustomAnnotation.RequirePermission annotation = method.getAnnotation(com.protasker.protasker_backend.utils.CustomAnnotation.RequirePermission.class);
        String permission = annotation.value();

        Long teamMemberId = resolver.resolveTeamMemberId();
        log.info("Checking [{}] for TeamMember ID [{}]", permission, teamMemberId);
        checker.check(teamMemberId, permission);

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
