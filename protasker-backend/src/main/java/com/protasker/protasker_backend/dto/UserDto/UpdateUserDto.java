package com.protasker.protasker_backend.dto.UserDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String department;
    private String position;
    private String phoneNumber;
    private List<String> skills;
}
