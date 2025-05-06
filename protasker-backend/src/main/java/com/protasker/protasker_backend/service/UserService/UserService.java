package com.protasker.protasker_backend.service.UserService;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.UserDto.UpdateUserDTO;
import com.protasker.protasker_backend.dto.UserDto.UserDTO;
import com.protasker.protasker_backend.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserDTO getUserByUserId(String userId);
    List<UserDTO> getAllUsers();
    GenericResponseDto updateUser(String id, UpdateUserDTO updateUserDTO);
    GenericResponseDto deactivateUser(String id);
    GenericResponseDto deleteUser(String id);

}
