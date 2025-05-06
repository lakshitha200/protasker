package com.protasker.protasker_backend.dto.UserDto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateUserDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String department;
    private String position;
    private String profilePicture;
    private String phoneNumber;
    private List<String> skills;
}
