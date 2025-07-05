package com.protasker.protasker_backend.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionContextResolver {

    private final HttpServletRequest request;

    public Long resolveTeamMemberId() {
        String header = request.getHeader("X-Team-Member-ID");
        if (header == null) {
            throw new IllegalStateException("Missing X-Team-Member-ID header");
        }
        return Long.parseLong(header);
    }
}

