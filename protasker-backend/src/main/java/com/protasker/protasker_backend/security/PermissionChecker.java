package com.protasker.protasker_backend.security;


import com.protasker.protasker_backend.cache.PermissionCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionChecker {

    private final PermissionCacheService cacheService;

    public void check(Long teamMemberId, String permissionCode) {
        var permissions = cacheService.getPermissions(teamMemberId);
        System.out.println("Permissions: "+permissions);
        if (!permissions.contains(permissionCode.toLowerCase())) {
            System.out.println("Works");
            throw new RuntimeException("Permission denied: " + permissionCode);
        }
    }

}

