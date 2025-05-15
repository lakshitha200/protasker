package com.protasker.protasker_backend.controller;

import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.UserDto.*;
import com.protasker.protasker_backend.service.UserService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/current-user")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        System.out.println("works");
        String username = principal.getName();
        System.out.println("Username: "+username);
        return ResponseEntity.ok( userService.findByUsername(username));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<List<UserDto>> (userService.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping("/{userID}")
    public ResponseEntity<GenericResponseDto> updateUser(@PathVariable String userID, @RequestBody UpdateUserDto updateUserDto) {
        return new ResponseEntity<>(userService.updateUser(userID,updateUserDto),HttpStatus.CREATED);
    }

    @PutMapping("/{userID}/email")
    public ResponseEntity<GenericResponseDto> updateEmail(@PathVariable String userID, @RequestBody UpdateEmailDto emailDto) {
        return new ResponseEntity<>(userService.updateEmail(userID,emailDto),HttpStatus.CREATED);
    }

    @PutMapping("/{userID}/password")
    public ResponseEntity<GenericResponseDto> updatePassword(@PathVariable String userID, @RequestBody UpdatePasswordDto passwordDto) {
        return new ResponseEntity<>(userService.updatePassword(userID,passwordDto),HttpStatus.CREATED);
    }

    @PutMapping("/{userID}/username")
    public ResponseEntity<GenericResponseDto> updateUsername(@PathVariable String userID, @RequestBody UpdateUsernameDto usernameDto) {
        return new ResponseEntity<>(userService.updateUsername(userID,usernameDto),HttpStatus.CREATED);
    }

    @PatchMapping("/{userID}/profile-picture")
    public ResponseEntity<GenericResponseDto> updateProfilePicture(
            @PathVariable String userID,
            @RequestParam("file") @Valid MultipartFile file) {
        return ResponseEntity.ok(userService.uploadProfilePicture(userID, file));
    }
}
