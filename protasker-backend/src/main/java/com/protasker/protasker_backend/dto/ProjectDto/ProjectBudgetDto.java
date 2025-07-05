package com.protasker.protasker_backend.dto.ProjectDto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectBudgetDto {
    private Long id;
    private Long projectId;
    private BigDecimal totalAmount;
    private String currency;
    private Long allocatedByUserId;
    private LocalDateTime createdAt;
}
