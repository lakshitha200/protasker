package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.AgileModel.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {}
