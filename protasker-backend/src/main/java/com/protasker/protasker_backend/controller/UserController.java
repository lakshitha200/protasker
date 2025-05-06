//package com.protasker.protasker_backend.controller;
//
//import com.protasker.protasker_backend.dto.GenericResponseDto;
//import com.protasker.protasker_backend.dto.UserDto.UpdateUserDTO;
//import com.protasker.protasker_backend.dto.UserDto.UserDTO;
//import com.protasker.protasker_backend.model.User;
//import com.protasker.protasker_backend.service.UserService.UserService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/user")
//@RequiredArgsConstructor
//@CrossOrigin("*")
//public class UserController {
//
//    private final UserService userService;
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<UserDTO> getUserByUserId(@PathVariable String userId){
//        System.out.println("works");
//        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<UserDTO>> getUserAllUsers(){
//        return new ResponseEntity<List<UserDTO>> (userService.getAllUsers(), HttpStatus.OK);
//    }
//
//    @PutMapping("/{userID}")
//    public ResponseEntity<GenericResponseDto> updateUser(@PathVariable String userID, @RequestBody UpdateUserDTO userDTO) {
//        return new ResponseEntity<>(userService.updateUser(userID, userDTO),HttpStatus.OK);
//    }
//
//
//
////    @GetMapping("/current-user")
////    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
////        User user = userService.findByUsername(userDetails.getUsername());
////        UserDTO currentUser = UserDTO.builder()
////                .userId(user.getUserId())
////                .username(user.getUsername())
////                .email(user.getEmail())
////                .firstName(user.getFirstName())
////                .lastName(user.getLastName())
////                .position(user.getPosition())
////                .status(user.getStatus())
////                .userType(user.getUserType())
////                .phoneNumber(user.getPhoneNumber())
////                .profilePicture(user.getProfilePicture())
////                .build();
////        return ResponseEntity.ok(currentUser);
////    }
//
//
//}
