package com.protasker.protasker_backend.service.impl;

import com.protasker.protasker_backend.constants.AuthConstants;
import com.protasker.protasker_backend.constants.UserConstants;
import com.protasker.protasker_backend.dto.AuthResponseDto;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.LoginRequestDto;
import com.protasker.protasker_backend.dto.RegisterRequestDto;
import com.protasker.protasker_backend.exception.CusExceptions.*;
import com.protasker.protasker_backend.model.RefreshToken;
import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.model.VerificationToken;
import com.protasker.protasker_backend.model.enums.Provider;
import com.protasker.protasker_backend.model.enums.TokenType;
import com.protasker.protasker_backend.model.enums.UserType;
import com.protasker.protasker_backend.repository.UserRepository;
import com.protasker.protasker_backend.repository.VerificationTokenRepository;
import com.protasker.protasker_backend.security.JwtService;
import com.protasker.protasker_backend.service.AuthService;
import com.protasker.protasker_backend.service.NotificationService.EmailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    @Override
    public GenericResponseDto register(RegisterRequestDto registerRequest) {
        if (registerRequest == null) {
            throw new IllegalArgumentException("Auth Service: "+AuthConstants.REGISTRATION_REQ_NULL.getMessage());
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("Auth Service: "+ UserConstants.USER_ALREADY_EXISTS.getMessage()+ " with the email");
        }
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException("Auth Service: "+ UserConstants.USER_ALREADY_EXISTS.getMessage()+ " with the username");
        }
        User newUser = null;
        try {
            // Save user in db
            newUser = userRepository.save(
                    User.builder()
                            .userId(generateUserId())
                            .username(registerRequest.getUsername())
                            .email(registerRequest.getEmail())
                            .password(passwordEncoder.encode(registerRequest.getPassword()))
                            .isActive(false)
                            .provider(Provider.LOCAL)
                            .userType(UserType.STANDARD)
                            .lastLogin(LocalDateTime.now())
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build()
                    );
        } catch (Exception e) {
            throw new RegistrationException("Auth Service: "+AuthConstants.REGISTRATION_FAIL.getMessage(), e);
        }

        // Send verification email
        String vToken = generateVerficationToken(newUser.getEmail());
        verificationTokenRepository.save(VerificationToken.builder().token(vToken).user(newUser).tokenType(TokenType.EMAIL_VERIFICATION).expiresAt(LocalDateTime.now().plusHours(24)).build());
        String isSent = emailService.sendVerificationEmail(newUser.getEmail(), vToken);
        System.out.println(isSent);

        return GenericResponseDto.builder()
                .response("Auth Service: "+AuthConstants.REGISTRATION_SUCCESS.getMessage() +" : User ID = "+newUser.getUserId())
                .code(HttpStatus.CREATED)
                .build();
    }

    @Transactional
    @Override
    public AuthResponseDto login(LoginRequestDto loginRequest) {
        User user = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(),loginRequest.getUsernameOrEmail())
                .orElseThrow(() -> new UserNotFoundException("Service login: Invalid username or email"));

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()
        ));

        try{
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtService.generateToken(authentication.getName());
            String refreshToken = jwtService.createRefreshToken(user).getToken();

            return AuthResponseDto.builder()
                    .responseMessage(AuthConstants.LOGIN_SUCCESS.getMessage())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

        } catch (AuthenticationException e) {
            throw new AuthException("Authentication failed", e);
        }catch (Exception e){
            throw new AuthException("Login processing error", e);
        }
    }

    @Override
    public AuthResponseDto refreshToken(String refreshToken) {
        RefreshToken token = jwtService.findByToken(refreshToken);

        if (token.getToken()==null || token.getToken().isEmpty() || jwtService.isTokenExpired(token)) {
            throw new TokensException("Invalid or expired refresh token");
        }

        User user = token.getUser();
        String newAccessToken = jwtService.generateToken(user.getUsername());
        return AuthResponseDto.builder()
                .responseMessage(AuthConstants.LOGIN_SUCCESS.getMessage())
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    @Override
    public void logout(String refreshToken) {
        RefreshToken token = jwtService.findByToken(refreshToken);
        jwtService.deleteByToken(token.getToken()); // Or token.setRevoked(true); refreshTokenRepository.save(token);
    }

//    @Transactional
    @Override
    public GenericResponseDto verifyEmail(String token) {
        System.out.println(LocalDateTime.now());
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokensException("Auth Service: "+AuthConstants.INVALID_TOKEN.getMessage()));

        if (verificationToken.isUsed() || verificationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokensException("Auth Service: "+AuthConstants.EXPIRED_TOKEN.getMessage());
        }

        User user = null;
        try{
            // Mark user as verified
            user = verificationToken.getUser();
            user.setIsActive(true);
            userRepository.save(user);

            // Invalidate token
            verificationToken.setUsed(true);
            verificationTokenRepository.save(verificationToken);
            System.out.println("Verification worked!");
        }catch (Exception e){
            throw new AuthException("Auth Service: Email Verification Error");
        }

        // Send welcome email
        String isSent = emailService.sendWelcomeEmail(user.getEmail());
        System.out.println(isSent);


        return GenericResponseDto.builder()
                .response(AuthConstants.EMAIL_VERIFICATION_SUCCESS.getMessage())
                .code(HttpStatus.OK).build();
    }

    @Transactional
    @Override
    public GenericResponseDto createResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: "+email));

        String token = UUID.randomUUID().toString();
        try{
            VerificationToken resetToken = VerificationToken.builder()
                    .token(token)
                    .user(user)
                    .tokenType(TokenType.PASSWORD_RESET)
                    .expiresAt(LocalDateTime.now().plusMinutes(15))
                    .used(false)
                    .build();

            verificationTokenRepository.save(resetToken);
        }catch (Exception e){
            throw new TokensException("Token Creation Fail");
        }

        emailService.sendPasswordRestEmail(user.getEmail(), token);
        return GenericResponseDto.builder()
                .response(AuthConstants.PASSWORD_RESET_SENT.getMessage())
                .code(HttpStatus.OK).build();
    }

    @Transactional
    @Override
    public GenericResponseDto resetPassword(String token, String newPassword) {
        VerificationToken resetToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokensException("Invalid token"));

        if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokensException("Token expired");
        }

        try {
            User user = resetToken.getUser();
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userRepository.save(user);
        }catch (Exception e){
            throw new UserManagementException("Password Rest Fail");
        }
//        verificationTokenRepository.delete(resetToken);
        return GenericResponseDto.builder()
                .response(AuthConstants.PASSWORD_RESET_SUCCESS.getMessage())
                .code(HttpStatus.OK).build();
    }

    @Override
    public GenericResponseDto resendVerifyEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: "+email));

        // Resend verification email
        String vToken = generateVerficationToken(email);
        verificationTokenRepository.save(VerificationToken.builder().token(vToken).user(user).tokenType(TokenType.EMAIL_VERIFICATION_RESEND).expiresAt(LocalDateTime.now().plusHours(24)).build());
        String isSent = emailService.sendVerificationEmail(user.getEmail(), vToken);
        System.out.println(isSent);

        return GenericResponseDto.builder()
                .response("Auth Service: "+AuthConstants.REGISTRATION_SUCCESS.getMessage() +" : User ID = "+user.getUserId())
                .code(HttpStatus.CREATED)
                .build();
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

    private String generateVerficationToken(String email){
        String jwtSecret = "af60addca9ea3e3c099551e1b6576c9966dce0a33de879dd7e160f86dbd872ca236d6e9ee66fb6e30039fe7c345324a10f3d0741b0600fa7a45df4c6691eff4f4209767ed39f51e37717d8feecd5dd14fc34ebe619e6a29ae91d9ffe134cb5718bec0b3680d6ae7fc09e67763fe7c05d05d3ba69f47211163852633755b7f861132b0c98f8d7c1af9152d547408e676867a0a32fb525a4354180f5fb6b2dc23b5faa4155b8db63385f96259a90b6ee0e74a5b90a4f0f4fa96fafc296c64588b5c009b3829ae2e1d69a1cf7569b50a65fa553350495d18816f785f961c970c0a9cb9c8da25cc5e9fa4a3e9527a132d616b232d1ee21c3bf6dc8d9e3376e2e82c0";

        return Jwts.builder()
                .setSubject(email) // email
                .claim("type", "email_verification")
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(SignatureAlgorithm.HS256, jwtSecret) // keep secret safe!
                .compact();
    }

    public static void main(String[] args) {

//        AuthServiceImpl authService = new AuthServiceImpl();
//        String x = AuthServiceImpl.generateVerficationToken("lakshithaf20@gmail.com");
//        System.out.println(x);

//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
    }

}
