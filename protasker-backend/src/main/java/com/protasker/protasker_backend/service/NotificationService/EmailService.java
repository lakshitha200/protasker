package com.protasker.protasker_backend.service.NotificationService;

public interface EmailService {
    String sendEmail();
    String sendEmailWithAttachment();
    String sendHtmlEmail();
    String sendVerificationEmail(String email, String token);
    String sendWelcomeEmail(String email);
    String sendPasswordRestEmail(String email, String token);
}
