package com.protasker.protasker_backend.service.UserService.UserServiceImpl;

import com.protasker.protasker_backend.constants.UserConstants;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.UserDto.*;
import com.protasker.protasker_backend.exception.CusExceptions.FileStorageException;
import com.protasker.protasker_backend.exception.CusExceptions.UserAlreadyExistsException;
import com.protasker.protasker_backend.exception.CusExceptions.UserNotFoundException;
import com.protasker.protasker_backend.exception.CusExceptions.UserServiceException;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.repository.UserRepository;
import com.protasker.protasker_backend.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto findByUsername(String username) {
        if (username == null && username.isEmpty()) {
            throw new UserServiceException("User Service: Username cannot be null or empty");
        }
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User Service: User not found with username: " + username)
        );

        return UserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .department(user.getDepartment())
                .position(user.getPosition())
                .profilePicture(user.getProfilePicture())
                .phoneNumber(user.getPhoneNumber())
                .skills(user.getSkills())
                .isActive(user.getIsActive())
                .userType(user.getUserType())
                .lastLogin(user.getLastLogin())
                .createdAt(user.getCreatedAt())
                .build();

    }

    @Override
    @Transactional
    public GenericResponseDto updateUser(String id, UpdateUserDto updateUserDto) {
        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("UserService: User not found with user-id: " + id));

        try {
            if (updateUserDto.getFirstName() != null) user.setFirstName(updateUserDto.getFirstName());
            if (updateUserDto.getLastName() != null) user.setLastName(updateUserDto.getLastName());
            if (updateUserDto.getDepartment() != null) user.setDepartment(updateUserDto.getDepartment());
            if (updateUserDto.getPosition() != null) user.setPosition(updateUserDto.getPosition());
            if (updateUserDto.getPhoneNumber() != null) user.setPhoneNumber(updateUserDto.getPhoneNumber());
            if (updateUserDto.getSkills() != null) user.setSkills(updateUserDto.getSkills());

            userRepository.save(user);
        } catch (Exception e) {
            throw new UserServiceException("User Service: User update fail");
        }

        return GenericResponseDto.builder().response(UserConstants.USER_UPDATED.getMessage())
                .code(HttpStatus.CREATED).build();
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        if (userId == null && userId.isEmpty()) {
            throw new UserServiceException("User Service: UserId cannot be null or empty");
        }
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new UserNotFoundException("User Service: User not found with userId: " + userId)
        );

        return UserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .department(user.getDepartment())
                .position(user.getPosition())
                .profilePicture(user.getProfilePicture())
                .phoneNumber(user.getPhoneNumber())
                .skills(user.getSkills())
                .isActive(user.getIsActive())
                .userType(user.getUserType())
                .lastLogin(user.getLastLogin())
                .build();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .userId(user.getUserId())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .department(user.getDepartment())
                        .position(user.getPosition())
                        .profilePicture(user.getProfilePicture())
                        .phoneNumber(user.getPhoneNumber())
                        .skills(user.getSkills())
                        .isActive(user.getIsActive())
                        .userType(user.getUserType())
                        .lastLogin(user.getLastLogin())
                        .build()
                ).toList();
    }

    @Transactional
    @Override
    public GenericResponseDto updateEmail(String userID, UpdateEmailDto emailDto) {
        User user = userRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundException("UserService: User not found with user-id: " + userID));

        if (emailDto.getEmail() != null && userRepository.existsByEmail(emailDto.getEmail())) {
            throw new UserAlreadyExistsException("UserService: Email already registered");
        }

        try {
            user.setEmail(emailDto.getEmail());
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserServiceException("UserService: User email update fail");
        }

        return GenericResponseDto.builder().response("UserService: User email update successfully")
                .code(HttpStatus.CREATED).build();
    }

    @Transactional
    @Override
    public GenericResponseDto updatePassword(String userID, UpdatePasswordDto passwordDto) {
        User user = userRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundException("UserService: User not found with user-id: " + userID));
        try {
            user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserServiceException("UserService: User password update fail");
        }

        return GenericResponseDto.builder().response("UserService: User password update successfully")
                .code(HttpStatus.CREATED).build();
    }

    @Transactional
    @Override
    public GenericResponseDto updateUsername(String userID, UpdateUsernameDto usernameDto) {
        if(usernameDto.getUsername()!=null && userRepository.existsByUsername(usernameDto.getUsername())){
            throw new UserAlreadyExistsException("UserService: Username already registered");
        }
        User user = userRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundException("UserService: User not found with user-id: " + userID));
        try {
            user.setUsername(usernameDto.getUsername());
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserServiceException("UserService: User username update fail");
        }

        return GenericResponseDto.builder().response("UserService: User username update successfully")
                .code(HttpStatus.CREATED).build();
    }

    @Transactional
    @Override
    public GenericResponseDto uploadProfilePicture(String userID, MultipartFile file) {
        if (userID == null || userID.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }
        User user = userRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundException("User not found with the userId: "+userID));

        // File validation
        validateFile(file);
        String fileName = "";

        try {
            Path rootLocation = Paths.get("D:/Bit project/ProTasker/protasker-frontend/public/uploads/profile-pictures");
            fileName = userID + "_" + System.currentTimeMillis() +
                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            System.out.println("------filename: "+fileName);
            // Save file locally
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
            Path targetPath = rootLocation.resolve(fileName).normalize();
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);

            // Clean up old file
            if (user.getProfilePicture() != null) {
                String oldFileName = user.getProfilePicture().substring(user.getProfilePicture().lastIndexOf('/') + 1);
                System.out.println("oldFileName: "+oldFileName);

                if(oldFileName.startsWith(userID + "_")){
                    System.out.println("yes");
                    Path oldImagePath = rootLocation.resolve(oldFileName).normalize();
                    System.out.println("oldImagePath: "+oldImagePath);
                    if (oldImagePath.startsWith(rootLocation.normalize())) {
                        System.out.println("111");
                        Files.deleteIfExists(oldImagePath);
                        System.out.println("workssss");
                    }
                    System.out.println("lolddd");
                }

            }
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage(), e);
        }

        // Update user
        String fileUrl = "/uploads/profile-pictures/" + fileName;
        user.setProfilePicture(fileUrl);
        userRepository.save(user);

        return GenericResponseDto.builder()
                .response("Profile picture uploaded successfully")
                .code(HttpStatus.CREATED)
                .build();

    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only images are allowed");
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("File size exceeds 5MB limit");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.asList("jpg", "jpeg", "png").contains(extension)) {
            throw new IllegalArgumentException("Only JPG, JPEG, PNG images are allowed");
        }
    }
}
