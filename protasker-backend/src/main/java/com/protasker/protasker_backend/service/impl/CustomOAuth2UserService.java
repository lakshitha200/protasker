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
import com.protasker.protasker_backend.service.NotificationService.EmailService;
import com.protasker.protasker_backend.service.impl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final HttpServletResponse response;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        // Extract user info
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String googleId = oAuth2User.getAttribute("sub");
        System.out.println("googleId: "+googleId);

        // Find or create user
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                        User newUser = User.builder()
                                .userId(generateUserId())
                                .username(name)
                                .email(email)
                                .password(null)
                                .isActive(true)
                                .provider(Provider.GOOGLE)
                                .googleId(googleId)
                                .userType(UserType.STANDARD)
                                .lastLogin(LocalDateTime.now())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build();

                        userRepository.save(newUser);

                        // Send welcome email
                        emailService.sendWelcomeEmail(newUser.getEmail());
                        return newUser;
                        }

                );


        try{
            String accessToken = jwtService.generateToken(name);
            String refreshToken = jwtService.createRefreshToken(user).getToken();

            // Step 3: Create HttpOnly cookies
            ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", accessToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .sameSite("Lax")
                    .maxAge(15 * 60)
                    .build();

            ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .sameSite("Lax")
                    .maxAge(7 * 24 * 60 * 60)
                    .build();

            // Step 4: Set cookies in the response header (The cookies will be automatically stored in the user's browser.)
            response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
            response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
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
