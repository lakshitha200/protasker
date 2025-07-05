package com.protasker.protasker_backend.utils.Mapper;

import com.protasker.protasker_backend.dto.AttachmentDto;
import com.protasker.protasker_backend.model.ProjectModel.Attachment;
import com.protasker.protasker_backend.model.ProjectModel.ProjectTeamMember;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper {

    public Attachment toEntity(AttachmentDto dto) {
        return Attachment.builder()
                .id(dto.getId())
                .projectType(dto.getProjectType())
                .entityType(dto.getEntityType())
                .entityId(dto.getEntityId())
                .documentType(dto.getDocumentType())
                .fileName(dto.getFileName())
                .fileUrl(dto.getFileUrl())
                .fileType(dto.getFileType())
                .fileSize(dto.getFileSize())
                .label(dto.getLabel())
                .metadata(dto.getMetadata())
                .uploadedBy(dto.getUploadedById() != null
                        ? ProjectTeamMember.builder().id(dto.getUploadedById()).build()
                        : null)
                .approvalStatus(dto.getApprovalStatus())
                .build();
    }

    public AttachmentDto toDto(Attachment attachment) {
        return AttachmentDto.builder()
                .id(attachment.getId())
                .projectType(attachment.getProjectType())
                .entityType(attachment.getEntityType())
                .entityId(attachment.getEntityId())
                .documentType(attachment.getDocumentType())
                .fileName(attachment.getFileName())
                .fileUrl(attachment.getFileUrl())
                .fileType(attachment.getFileType())
                .fileSize(attachment.getFileSize())
                .label(attachment.getLabel())
                .metadata(attachment.getMetadata())
                .uploadedById(attachment.getUploadedBy() != null ? attachment.getUploadedBy().getId() : null)
                .approvalStatus(attachment.getApprovalStatus())
                .build();
    }
}
