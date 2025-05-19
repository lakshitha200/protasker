package com.protasker.protasker_backend.service.AuthService;

import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.enums.Provider;
import com.protasker.protasker_backend.model.enums.UserType;
import com.protasker.protasker_backend.repository.UserRepository;
import com.protasker.protasker_backend.security.JwtService;
import com.protasker.protasker_backend.service.NotificationService.EmailService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
        String pictureUrl = oAuth2User.getAttribute("picture");
        String username = name != null ? name.replace(" ", "_").toLowerCase() : null;

        // Find or create user
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                        User newUser = User.builder()
                                .userId(generateUserId())
                                .username(username)
                                .email(email)
                                .password(null)
                                .isActive(true)
                                .provider(Provider.GOOGLE)
                                .googleId(googleId)
                                .profilePicture(pictureUrl)
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
        return oAuth2User;
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
