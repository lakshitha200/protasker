package com.protasker.protasker_backend.service.UserService;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.UserDto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    UserDto findByUsername(String username);
    GenericResponseDto updateUser(String id, UpdateUserDto updateUserDto);

    UserDto getUserByUserId(String userId);

    List<UserDto> getAllUsers();

    GenericResponseDto updateEmail(String userID, UpdateEmailDto emailDto);

    GenericResponseDto updatePassword(String userID, UpdatePasswordDto passwordDto);

    GenericResponseDto updateUsername(String userID, UpdateUsernameDto usernameDto);

    GenericResponseDto uploadProfilePicture(String userID, MultipartFile file);
}
