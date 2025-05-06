package com.protasker.protasker_backend.controller;


import com.protasker.protasker_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;
//    @GetMapping("/user")
////    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public RegisterRequestDto getEmployee(){
//        User user = userRepository.findById(1L).orElseThrow(
//                ()-> new RuntimeException()
//        );
//        System.out.println(user);
//        RegisterRequestDto requestDto = new RegisterRequestDto();
//        requestDto.setUsername(user.getUsername());
//        requestDto.setRoles(user.getRoles());
//        return requestDto;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Map User to UserDto
//        Set<RoleDto> roleDtos = user.getRoles().stream()
//                .map(role -> new RoleDto())  // Assuming roleName is enum
//                .collect(Collectors.toSet());
//
//        UserDto userDto = new UserDto();
//        userDto.setUsername(user.getUsername());
//        userDto.setFirstName(user.getFirstName());
//        userDto.setLastName(user.getLastName());
//        userDto.setRoles(roleDtos);
//
//        return ResponseEntity.ok(userDto);
//    }
//    @GetMapping("/user")
//    public UserDTO getAUser(){
//        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
//        Set<RoleDTO> roleDtos = user.getRoles().stream()
//                .map(role -> RoleDTO.builder()
//                        .id(role.getId())
//                        .name(role.getName())
//                        .description(role.getDescription())
//                        .isSystemRole(role.getIsSystemRole())
//                        .createdAt(role.getCreatedAt())
//                        .build())
//                .collect(Collectors.toSet());
//
//        return UserDTO.builder()
//                .userId(user.getUserId())
//                .userType(user.getUserType())
//                .roles(roleDtos)
//                .build();
//    }
//
//    @GetMapping("/user/all")
//    public Set<UserDTO> getAllUsers(){
//        List<User> user = userRepository.findAll();
//
//        return user.stream()
//                .map(user1 -> UserDTO.builder()
//                        .userId(user1.getUserId())
//                        .userType(user1.getUserType())
//                        .roles(user1.getRoles().stream().map(role -> RoleDTO.builder()
//                                .id(role.getId())
//                                .name(role.getName())
//                                .description(role.getDescription())
//                                .isSystemRole(role.getIsSystemRole())
//                                .createdAt(role.getCreatedAt())
//                                .build()).collect(Collectors.toSet()))
//                        .build())
//                .collect(Collectors.toSet());
//    }
//    @GetMapping("/user")
//    public User getEmployeeById(long id) {
//        Optional<User> employee =  U.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }else {
//            throw new RuntimeException();
//        }
//    }
//    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String saveEmployees(){
//        return "You saved a Employee";
//    }
//
//    @PutMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String updateEmployees(){
//        return "You updated a Employee";
//    }

    @GetMapping
    public String test(){
        return "Test Success!";
    }
}

