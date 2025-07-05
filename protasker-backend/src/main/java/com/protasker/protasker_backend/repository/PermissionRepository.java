package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
