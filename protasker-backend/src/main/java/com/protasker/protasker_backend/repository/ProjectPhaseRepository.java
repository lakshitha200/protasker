package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.WaterfallModel.ProjectPhase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectPhaseRepository extends JpaRepository<ProjectPhase, Long> {}