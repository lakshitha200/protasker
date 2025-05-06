package com.protasker.protasker_backend.constants;

import lombok.Getter;

@Getter
public enum AuthConstants {

    // Success Messages
    REGISTRATION_SUCCESS("Registration success"),
    EMAIL_VERIFICATION_SENT("Verification email sent"),
    EMAIL_VERIFICATION_SUCCESS ("Email verified successfully"),
    PASSWORD_RESET_SENT("Password rest link sent"),

    LOGIN_SUCCESS("Logged in successfully"),
    LOGOUT_SUCCESS("Logged out successfully"),
    REFRESH_TOKEN_SUCCESS("Token refreshed successfully"),
    PASSWORD_RESET_SUCCESS("Password reset successfully"),

    EMAIL_VERIFICATION_RESENT("Verification email resent"),
    ACCOUNT_ACTIVATED("Account activated successfully"),
    TWO_FACTOR_ENABLED("Two-factor authentication enabled"),
    TWO_FACTOR_DISABLED("Two-factor authentication disabled"),
    DEVICE_REVOKED("Device access revoked"),
    SESSION_CLEARED("All sessions cleared"),

    // Error Messages
    VERIFICATION_EMAIL_FAILED("Failed to send verification email"),
    REGISTRATION_REQ_NULL("Registration request cannot be null"),
    REGISTRATION_FAIL("User Registration failed"),
    INVALID_TOKEN("Invalid or expired token"),
    EXPIRED_TOKEN("Token has expired"),

    LOGIN_FAILED("Invalid email or password"),
    ACCOUNT_LOCKED("Account locked due to multiple failed attempts"),


    INVALID_REFRESH_TOKEN("Invalid refresh token"),
    UNAUTHORIZED_ACCESS("Unauthorized access"),
    FORBIDDEN_ACCESS("Insufficient permissions"),
    PASSWORD_RESET_FAILED("Password reset failed"),

    TWO_FACTOR_SETUP_FAILED("Two-factor setup failed"),
    TWO_FACTOR_VERIFICATION_FAILED("Invalid verification code"),
    DEVICE_NOT_RECOGNIZED("Unrecognized device"),
    SESSION_NOT_FOUND("Session not found"),

    // Validation Messages
    INVALID_EMAIL_OR_PASSWORD("Invalid email or password format"),
    PASSWORD_REUSE_NOT_ALLOWED("Cannot reuse previous passwords"),
    PASSWORD_CHANGE_REQUIRED("Password change required"),
    TOKEN_MISSING("Authorization token missing"),

    // Security Messages
    BRUTE_FORCE_BLOCK("Too many attempts, try again later"),
    IP_BLOCKED("IP address blocked temporarily"),
    SUSPICIOUS_ACTIVITY("Suspicious activity detected"),
    SESSION_EXPIRED("Session expired, please log in again"),
    CONCURRENT_SESSION_LIMIT("Maximum concurrent sessions reached");

    private final String message;

    AuthConstants(String message) {
        this.message = message;
    }
}