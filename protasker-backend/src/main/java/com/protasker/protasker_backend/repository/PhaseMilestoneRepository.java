package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.WaterfallModel.PhaseMilestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseMilestoneRepository extends JpaRepository<PhaseMilestone, Long> {}
