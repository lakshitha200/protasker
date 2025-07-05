package com.protasker.protasker_backend.repository;


import com.protasker.protasker_backend.model.ReportsModel.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportId(String reportId);
}
