package com.protasker.protasker_backend.dto.Notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.protasker.protasker_backend.model.Notification;
import com.protasker.protasker_backend.model.enums.ProjectEnums.Channel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

    @JsonProperty("sender_id")
    private Long senderId;

    @NotNull(message = "Receiver ID is required")
    @JsonProperty("receiver_id")
    private Long receiverId;

    @Size(max = 50, message = "Entity type must be less than 50 characters")
    @JsonProperty("entity_type")
    private String entityType;

    @JsonProperty("entity_id")
    private Long entityId;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;

    @NotBlank(message = "Message is required")
    private String message;

    @JsonProperty("channel")
    private Channel channel = Channel.IN_APP;

    @Size(max = 512, message = "Action URL must be less than 512 characters")
    @JsonProperty("action_url")
    private String actionUrl;

//    @JsonProperty("metadata")
//    private Map<String, Object> metadata;
}