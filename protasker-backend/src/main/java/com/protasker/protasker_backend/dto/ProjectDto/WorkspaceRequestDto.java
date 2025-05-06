package com.protasker.protasker_backend.dto.ProjectDto;

import com.protasker.protasker_backend.model.enums.WorkspaceType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkspaceRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotBlank(message = "Type is required")
    private String type;
    private String clientId;
    private String logo_url;
}
