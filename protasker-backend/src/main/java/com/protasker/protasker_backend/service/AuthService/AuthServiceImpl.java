package com.protasker.protasker_backend.service.AuthService;

import com.protasker.protasker_backend.constants.AuthConstants;
import com.protasker.protasker_backend.constants.UserConstants;
import com.protasker.protasker_backend.dto.AuthDto.PasswordResetDto;
import com.protasker.protasker_backend.dto.GenericResponseDto;
import com.protasker.protasker_backend.dto.AuthDto.LoginRequestDto;
import com.protasker.protasker_backend.dto.AuthDto.RegisterRequestDto;
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
import com.protasker.protasker_backend.service.NotificationService.EmailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
            throw new IllegalArgumentException("Registration request cannot be null");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with the email");
        }
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException("User already exists with the username");
        }
            // Save user in db
        User newUser = userRepository.save(
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

        // Send verification email
        String vToken = generateVerficationToken(newUser.getEmail());
        verificationTokenRepository.save(VerificationToken.builder().token(vToken).user(newUser).tokenType(TokenType.EMAIL_VERIFICATION).expiresAt(LocalDateTime.now().plusHours(24)).build());
        emailService.sendVerificationEmail(newUser.getEmail(), vToken);

        return GenericResponseDto.builder()
                .response("Registration success : User ID = "+newUser.getUserId())
                .code(HttpStatus.CREATED)
                .build();
    }

    @Transactional
    @Override
    public GenericResponseDto login(LoginRequestDto loginRequest, HttpServletResponse response) {
        // Step 1: Authenticate user
        User user = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid username or email"));

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()
        ));

        try {
            // Set authentication context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Step 2: Generate access token and refresh token
            String accessToken = jwtService.generateToken(authentication.getName());
            String refreshToken = jwtService.createRefreshToken(user).getToken();

            // Step 3: Create HttpOnly cookies
            ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", accessToken)
                    .httpOnly(true)  // Ensures the cookie is not accessible via JavaScript(true)
                    .secure(false)    // Ensures the cookie is only sent over HTTPS(true)
                    .path("/")       // The cookie will be available for all paths
                    .sameSite("Lax")  // Prevents the cookie from being sent in cross-origin requests
                    .maxAge(15 * 60)  // Access token expiration time (15 minutes)
                    .build();

            ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")  // Path for the refresh token (can be adjusted as needed)
                    .sameSite("Lax")
                    .maxAge(7 * 24 * 60 * 60)  // Refresh token expiration time (7 days)
                    .build();

            // Step 4: Set cookies in the response header (The cookies will be automatically stored in the user's browser.)
            response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
            response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

            // Step 5: Return success response with the appropriate message
            return GenericResponseDto.builder()
                    .response("Login Success")
                    .code(HttpStatus.OK)
                    .build();

        } catch (AuthenticationException e) {
            throw new AuthException("Authentication failed", e);
        } catch (Exception e) {
            throw new AuthException("Login processing error", e);
        }
    }



    @Override
    public GenericResponseDto refreshToken(HttpServletRequest request, HttpServletResponse response) {
//        String rToken = ;
//        if(rToken )
        RefreshToken token = jwtService.findByToken(jwtService.extractRefreshTokenFromCookies(request));
        System.out.println("Token: "+ token);
        if (token.getToken()==null || token.getToken().isEmpty() || jwtService.isTokenExpired(token)) {
            throw new TokensException("Invalid or expired refresh token", HttpStatus.BAD_REQUEST);
        }

        User user = token.getUser();
        String newAccessToken = jwtService.generateToken(user.getUsername());
        // 4. Set new access token in HTTP-only cookie (optional)
        ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", newAccessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(15 * 60)  // Access token expiration time (15 minutes)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        return GenericResponseDto.builder()
                .response("Refresh Success")
                .code(HttpStatus.OK)
                .build();
    }

    @Transactional
    @Override
    public void logout(String refreshToken) {
        RefreshToken token = jwtService.findByToken(refreshToken);
        jwtService.deleteByToken(token.getToken()); // Or token.setRevoked(true); refreshTokenRepository.save(token);
    }

    @Transactional
    @Override
    public GenericResponseDto verifyEmail(String token) {
        System.out.println(LocalDateTime.now());
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokensException("Invalid or expired token", HttpStatus.BAD_REQUEST));

        if (verificationToken.isUsed()) {
            throw new TokensException("Token was already used", HttpStatus.BAD_REQUEST);
        }

        if (verificationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokensException("Token has expired", HttpStatus.GONE);
        }

        User user = verificationToken.getUser();
        user.setIsActive(true);
        userRepository.save(user);

        // Invalidate token
        verificationToken.setUsed(true);
        verificationTokenRepository.save(verificationToken);
        System.out.println("Verification worked!");

        // Send welcome email
//        emailService.sendWelcomeEmail(user.getEmail());

        return GenericResponseDto.builder()
                .response("Email verified successfully")
                .code(HttpStatus.OK).build();
    }

    @Transactional
    @Override
    public GenericResponseDto createResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: "+email));

        String token = UUID.randomUUID().toString();

        VerificationToken resetToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .tokenType(TokenType.PASSWORD_RESET)
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .used(false)
                .build();

        verificationTokenRepository.save(resetToken);

        emailService.sendPasswordRestEmail(user.getEmail(), token);
        return GenericResponseDto.builder()
                .response("Password rest link sent")
                .code(HttpStatus.OK).build();
    }

    @Transactional
    @Override
    public GenericResponseDto resetPassword(PasswordResetDto passwordResetDto) {
        VerificationToken resetToken = verificationTokenRepository.findByToken(passwordResetDto.getToken())
                .orElseThrow(() -> new TokensException("Invalid token", HttpStatus.BAD_REQUEST));

        if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokensException("Token expired", HttpStatus.BAD_REQUEST);
        }

        User user = resetToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(passwordResetDto.getNewPassword()));
        userRepository.save(user);

        resetToken.setUsed(true);
        verificationTokenRepository.save(resetToken);

        //password change success email
        return GenericResponseDto.builder()
                .response("Password reset successfully")
                .code(HttpStatus.OK).build();
    }

    @Transactional
    @Override
    public GenericResponseDto resendVerifyEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: "+email));

        // Resend verification email
        String vToken = generateVerficationToken(email);
        verificationTokenRepository.save(VerificationToken.builder().token(vToken).user(user).tokenType(TokenType.EMAIL_VERIFICATION_RESEND).expiresAt(LocalDateTime.now().plusHours(24)).build());
        emailService.sendVerificationEmail(user.getEmail(), vToken);

        return GenericResponseDto.builder()
                .response("Verification email sent")
                .code(HttpStatus.CREATED)
                .build();
    }

    @Override
    public boolean checkAuthStatus(HttpServletRequest request) {
        String token = jwtService.extractAccessTokenFromCookies(request);
        boolean isValid = false;
        if(token!=null){
            isValid = jwtService.validateToken(token);
            return isValid;
        }else{
            String rToken = jwtService.extractRefreshTokenFromCookies(request);
            if(rToken!=null){
                isValid = jwtService.validateToken(rToken);
                return isValid;
            }else{
                System.out.println("checkAuthStatus isValid----: "+isValid);
                return isValid;
            }
        }
    }

    private String generateUserId() {
        // Get the current year and month
        String yearMonth = String.format("%02d", java.time.LocalDate.now
                ().getYear() % 100) + String.format("%02d", java.time.LocalDate.now().getMonthValue());

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
