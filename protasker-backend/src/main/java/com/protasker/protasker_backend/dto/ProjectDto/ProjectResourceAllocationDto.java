package com.protasker.protasker_backend.dto.ProjectDto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResourceAllocationDto {
    private Long id;
    private Long projectTeamMemberId;
    private BigDecimal allocationPercentage;
    private LocalDate allocationStart;
    private LocalDate allocationEnd;
}

