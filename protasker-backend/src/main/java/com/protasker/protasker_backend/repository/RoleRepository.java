package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
