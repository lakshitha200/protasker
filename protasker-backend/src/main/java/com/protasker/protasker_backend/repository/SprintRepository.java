package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.AgileModel.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {}