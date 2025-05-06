package com.protasker.protasker_backend.constants;
import lombok.Getter;

@Getter
public enum UserConstants {

    // Success Messages
    USER_CREATED("User created successfully"),
    USER_UPDATED("User updated successfully"),
    USER_DELETED("User deleted successfully"),
    USER_STATUS_CHANGED("User status updated"),
    USER_PROFILE_UPDATED("Profile updated successfully"),
    USER_AVATAR_UPDATED("Profile picture updated"),
    USER_PASSWORD_CHANGED("Password changed successfully"),
    USER_EMAIL_VERIFIED("Email verified successfully"),
    USER_RECOVERY_EMAIL_SENT("Password recovery email sent"),
    USER_SESSION_REVOKED("Session revoked successfully"),
    USER_PREFERENCES_SAVED("Preferences saved successfully"),
    USER_THEME_UPDATED("Theme preference updated"),
    USER_NOTIFICATIONS_UPDATED("Notification settings updated"),

    // Error Messages
    USER_CREATION_FAILED("User creation failed"),
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists"),
    USER_EMAIL_EXISTS("Email already registered"),
    USERNAME_EXISTS("Username already taken"),
    USER_INACTIVE("User account is inactive"),
    USER_SUSPENDED("User account is suspended"),
    USER_DELETED_ALREADY("User account is already deleted"),
    USER_ACCESS_DENIED("You don't have permission for this action"),
    USER_AUTH_FAILED("Authentication failed"),
    USER_PASSWORD_MISMATCH("Current password is incorrect"),
    USER_PASSWORD_WEAK("Password does not meet requirements"),
    USER_EMAIL_NOT_VERIFIED("Email address not verified"),
    USER_RECOVERY_FAILED("Password recovery failed"),
    USER_AVATAR_UPLOAD_FAILED("Profile picture upload failed"),
    USER_LIMIT_REACHED("Maximum users limit reached"),
    USER_INVALID_ROLE("Invalid user role specified"),

    // Validation Messages
    USER_INVALID_EMAIL("Invalid email format"),
    USER_INVALID_USERNAME("Username contains invalid characters"),
    USER_INVALID_PHONE("Invalid phone number format"),
    USER_INVALID_NAME("Name contains invalid characters"),
    USER_INVALID_CREDENTIALS("Invalid login credentials"),

    // Security Messages
    USER_SESSION_EXPIRED("Session has expired"),
    USER_2FA_REQUIRED("Two-factor authentication required"),
    USER_2FA_FAILED("Two-factor authentication failed"),
    USER_IP_BLOCKED("Access blocked from this IP address"),
    USER_RATE_LIMITED("Too many requests, please try again later"),
    USER_PASSWORD_RESET_REQUIRED("Password reset required");

    private final String message;

    UserConstants(String message) {
        this.message = message;
    }
}
