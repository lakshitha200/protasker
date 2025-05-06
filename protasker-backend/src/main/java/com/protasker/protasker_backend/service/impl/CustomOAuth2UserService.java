package com.protasker.protasker_backend.service.impl;

import com.protasker.protasker_backend.constants.AuthConstants;
import com.protasker.protasker_backend.dto.AuthResponseDto;
import com.protasker.protasker_backend.exception.CusExceptions.AuthException;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.enums.Provider;
import com.protasker.protasker_backend.model.enums.UserType;
import com.protasker.protasker_backend.repository.UserRepository;
import com.protasker.protasker_backend.security.JwtService;
import com.protasker.protasker_backend.service.AuthService;
import com.protasker.protasker_backend.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        // Extract user info
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        System.out.println(name);
        System.out.println(email);

        // Find or create user
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .userId(generateUserId())
                                .username(name)
                                .email(email)
                                .password(null)
                                .isActive(true)
                                .provider(Provider.GOOGLE)
                                .userType(UserType.STANDARD)
                                .lastLogin(LocalDateTime.now())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build()
//                                .provider(Provider.GOOGLE)
                ));


        try{
            String accessToken = jwtService.generateToken(name);
            String refreshToken = jwtService.createRefreshToken(user).getToken();
//            return AuthResponseDto.builder()
//                    .responseMessage(AuthConstants.LOGIN_SUCCESS.getMessage())
//                    .accessToken(accessToken)
//                    .refreshToken(refreshToken)
//                    .build();

        }catch (Exception e){
            throw new AuthException("Error Creating Tokens", e);
        }
        return oAuth2User;
//        return new CustomOAuth2User(oAuth2User, jwtToken);
    }

    private String generateUserId() {
        // Get the current year and month
        String yearMonth = String.format("%02d", java.time.LocalDate.now().getYear() % 100) + String.format("%02d", java.time.LocalDate.now().getMonthValue());

        // Generate random characters (digits and letters)
        Random random = new Random();
        StringBuilder randomPart = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            // Randomly choose between digits or uppercase letters
            if (random.nextBoolean()) {
                // Add random digit (0-9)
                randomPart.append(random.nextInt(10));
            } else {
                // Add random uppercase letter (A-Z)
                randomPart.append((char) ('A' + random.nextInt(26)));
            }
        }

        // Combine yearMonth and random part
        return "USR-" + yearMonth + "-" + randomPart.toString();
    }

}
