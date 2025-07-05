package com.protasker.protasker_backend.dto;

import com.protasker.protasker_backend.model.enums.ApprovalStatus;
import com.protasker.protasker_backend.model.enums.DocumentType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachmentDto {
    private Long id;
    private String projectType;
    private String entityType;
    private Long entityId;
    private DocumentType documentType;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Integer fileSize;
    private String label;
    private String metadata;
    private Long uploadedById;
    private ApprovalStatus approvalStatus;
}

