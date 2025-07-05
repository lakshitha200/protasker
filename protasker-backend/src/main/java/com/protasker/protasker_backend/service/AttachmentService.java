package com.protasker.protasker_backend.service;

import com.protasker.protasker_backend.dto.AttachmentDto;

import java.util.List;

public interface AttachmentService {
    AttachmentDto upload(AttachmentDto dto);
    List<AttachmentDto> getByEntity(String entityType, Long entityId);
    String delete(Long id);
}
