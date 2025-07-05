package com.protasker.protasker_backend.dto.ProjectDto;


import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectExpenseDto {
    private Long id;
    private Long projectId;
    private BigDecimal amount;
    private String description;
    private String category;
    private LocalDate expenseDate;
    private Long submittedByUserId;
    private Boolean approved;
    private Instant createdAt;
}


