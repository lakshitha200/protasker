package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.ReportsModel.ReportFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportFileRepository extends JpaRepository<ReportFile, Long> {
    List<ReportFile> findByReportId(Long reportId);
}

