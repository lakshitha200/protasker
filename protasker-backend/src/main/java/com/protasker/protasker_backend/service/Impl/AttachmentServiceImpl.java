package com.protasker.protasker_backend.service.Impl;

import com.protasker.protasker_backend.dto.AttachmentDto;
import com.protasker.protasker_backend.model.ProjectModel.Attachment;
import com.protasker.protasker_backend.repository.AttachmentRepository;
import com.protasker.protasker_backend.service.AttachmentService;
import com.protasker.protasker_backend.utils.Mapper.AttachmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository repository;
    private final AttachmentMapper mapper;

    @Override
    public AttachmentDto upload(AttachmentDto dto) {
        Attachment saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public List<AttachmentDto> getByEntity(String entityType, Long entityId) {
        return repository.findByEntityTypeAndEntityId(entityType, entityId)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "Attachment deleted successfully.";
    }
}
