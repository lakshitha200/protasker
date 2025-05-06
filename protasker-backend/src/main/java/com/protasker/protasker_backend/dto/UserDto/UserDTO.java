package com.protasker.protasker_backend.dto.UserDto;

import com.protasker.protasker_backend.model.enums.UserStatus;
import com.protasker.protasker_backend.model.enums.UserType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {
    private Long id;
    private String userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String department;
    private String position;
    private String status;
    private String profilePicture;
    private String phoneNumber;
    private List<String> skills;
    private boolean isActive;
    private String provider;
    private String userType;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}