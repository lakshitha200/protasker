//package com.protasker.protasker_backend.service.UserService.UserServiceImpl;
//
//import com.protasker.protasker_backend.constants.UserConstants;
//import com.protasker.protasker_backend.dto.GenericResponseDto;
//import com.protasker.protasker_backend.dto.UserDto.UpdateUserDTO;
//import com.protasker.protasker_backend.dto.UserDto.UserDTO;
//import com.protasker.protasker_backend.exception.CusExceptions.UserAlreadyExistsException;
//import com.protasker.protasker_backend.exception.CusExceptions.UserNotFoundException;
//import com.protasker.protasker_backend.model.User;
//import com.protasker.protasker_backend.repository.UserRepository;
//import com.protasker.protasker_backend.service.UserService.UserService;
//import jakarta.persistence.Table;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final ModelMapper modelMapper;
//
//
//    @Transactional(readOnly = true)
//    @Override
//    public UserDTO getUserByUserId(String userId) {
//        System.out.println("userId "+userId);
//        Optional<User> userOptional = userRepository.findByUserId(userId);
//        System.out.println("User present: " + userOptional.isPresent());
//
////        System.out.println("user: "+user.getUserId());
////        UserDTO dto = modelMapper.map(user, UserDTO.class);
////        System.out.println("DTO content: " + dto.toString());
//        return null;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<UserDTO> getAllUsers() {
//        System.out.println("2works");
//        return userRepository.findAll().stream()
//                .map(user -> modelMapper.map(user, UserDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public GenericResponseDto updateUser(String id, UpdateUserDTO updateUserDTO) {
//        User user = userRepository.findByUserId(id)
//                .orElseThrow(() -> new UserNotFoundException("UserService: "+UserConstants.USER_NOT_FOUND.getMessage()));
//
//        if(updateUserDTO.getEmail()!=null && !updateUserDTO.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(updateUserDTO.getEmail())){
//            throw new UserAlreadyExistsException("UserService: " + UserConstants. USER_EMAIL_EXISTS.getMessage());
//        }
//
//        user.setEmail(updateUserDTO.getEmail());
//        user.setFirstName(updateUserDTO.getFirstName());
//        user.setLastName(updateUserDTO.getLastName());
//        user.setDepartment(updateUserDTO.getDepartment());
//        user.setPosition(updateUserDTO.getPosition());
//        user.setProfilePicture(updateUserDTO.getProfilePicture());
//        user.setPhoneNumber(updateUserDTO.getPhoneNumber());
//        user.setSkills(updateUserDTO.getSkills());
//
//        userRepository.save(user);
//        return GenericResponseDto.builder().response(UserConstants.USER_UPDATED.getMessage())
//                .code(HttpStatus.CREATED).build();
//    }
//
//    @Override
//    @Transactional
//    public GenericResponseDto deactivateUser(String id) {
//        User user = userRepository.findByUserId(id)
//                .orElseThrow(() -> new UserNotFoundException("UserService: "+UserConstants.USER_NOT_FOUND.getMessage()));
//        user.setIsActive(false);
//        userRepository.save(user);
//        return GenericResponseDto.builder()
//                .response(UserConstants.USER_STATUS_CHANGED.getMessage())
//                .code(HttpStatus.OK).build();
//    }
//
//    @Override
//    @Transactional
//    public GenericResponseDto deleteUser(String id) {
//        User user = userRepository.findByUserId(id)
//                .orElseThrow(() -> new UserNotFoundException("UserService: "+UserConstants.USER_NOT_FOUND.getMessage()));
//        userRepository.delete(user);
//        return GenericResponseDto.builder()
//                .response(UserConstants.USER_DELETED.getMessage())
//                .code(HttpStatus.OK).build();
//    }
//
//}
