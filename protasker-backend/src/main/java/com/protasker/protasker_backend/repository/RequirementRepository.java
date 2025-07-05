package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.WaterfallModel.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement, Long> {}
