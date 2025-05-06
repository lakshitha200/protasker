package com.protasker.protasker_backend.service.NotificationService;

import com.protasker.protasker_backend.constants.AuthConstants;
import com.protasker.protasker_backend.exception.CusExceptions.EmailException;
import com.protasker.protasker_backend.service.AuthService;
import com.protasker.protasker_backend.utils.EmailTemplates;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender mailSender;
    private final EmailTemplates emailTemplates;
//    @Value("${spring.mail.username}")
//    public String fromEmail;

    /*
    ❌ Blocking: mailSender.send() blocks the thread.
    ✅ Fix: Add @Async:
        @Async
        public void sendEmail() { ... }
     */

    @Override
    public String sendEmail() {
        try {


            // Create a MIME (Multipurpose Internet Mail Extensions) message (supports attachments)
            SimpleMailMessage message = new SimpleMailMessage();
            //validate email
//            if (!toEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//                return "Invalid email!";
//            }
            message.setFrom("lakshithakapruka@gmail.com");
            message.setTo("lakshithaf20@gmail.com");
            message.setSubject("Simple test email from LAKSHITHA!");
            message.setText("This is a sample email body for my first email!");

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String sendEmailWithAttachment() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            //2. Helper to set email details + attachment
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("lakshithakapruka@gmail.com");
//            helper.setTo("lakshithaf20@gmail.com");
            helper.setSubject("Java email with attachment | From GC");
            helper.setText("Please find the attached documents below");

            helper.setTo(new String[] {"lakshithaf20@gmail.com", "myd33838@gmail.com"}); // To multiple
            helper.setCc(new String[] {"scateyghost@gmail.com"});
            helper.setBcc(new String[] {"ehashofficial@gmail.com"});

            //FileSystemResource file = new FileSystemResource(new File(attachmentPath))
            File file = new File("C:\\Users\\lakshithag\\Desktop\\images.png");
            System.out.println("file: "+file);
            if (!file.exists()) {
                return "Error: File not found!";
            }
            helper.addAttachment("logo.png", file);

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String sendHtmlEmail() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String htmlContent =
                    "<h1>Welcome! 👋</h1>" +  // Emoji works!
                            "<p>This email has a <strong>logo</strong> below: </p>" +
                            "<img src='cid:companyLogo'/>" +  // 'cid' = embedded image
                            "<p>Thank you! ❤️</p>";

            helper.setFrom("lakshithakapruka@gmail.com");
            helper.setTo("lakshithaf20@gmail.com");
            helper.setSubject("Java email with Html | From GC");

//            try (var inputStream = Objects.requireNonNull(EmailController.class.getResourceAsStream("/templates/email-content.html"))) {
//                helper.setText(
//                        new String(inputStream.readAllBytes(), StandardCharsets.UTF_8),
//                        true
//                );
//            }

            helper.addInline("companyLogo", new File("C:\\Users\\lakshithag\\Desktop\\images.png"));
            helper.setText(htmlContent,true);

            mailSender.send(message);
            return "success!";
        } catch (MailException | MessagingException e) {
            return e.getMessage();
        }
    }

    @Override
    public String sendVerificationEmail(String email, String token) {

        if(email== null && token == null && email.isEmpty() && token.isEmpty()){
            throw new NullPointerException("Email Service: Argument cannot be null or empty");
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String htmlContent = emailTemplates.getVerificationEmailHtmlContext(token);

            email = "lakshithaf20@gmail.com";

            helper.setFrom("lakshithakapruka@gmail.com");
            helper.setTo(email);
            helper.setSubject("Verify Your Email");
            helper.setText(htmlContent,true);

            mailSender.send(message);
            return AuthConstants. EMAIL_VERIFICATION_SENT.getMessage();
        } catch (Exception e) {
            throw new EmailException("Email Service: "+AuthConstants.VERIFICATION_EMAIL_FAILED.getMessage(),e);
        }
    }

    @Override
    public String sendWelcomeEmail(String email) {
        if(email== null && email.isEmpty()){
            throw new NullPointerException("Email Service: Argument cannot be null or empty");
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String htmlContent = emailTemplates.getWelcomeEmailHtmlContext();

            email = "lakshithaf20@gmail.com";

            helper.setFrom("lakshithakapruka@gmail.com");
            helper.setTo(email);
            helper.setSubject("Welcome to ProTasker!");
            helper.setText(htmlContent,true);

            mailSender.send(message);
            return "Welcome Email Sent";
        } catch (Exception e) {
            throw new EmailException("Email Service: Welcome Email Sent Fail",e);
        }
    }


    @Override
    public String sendPasswordRestEmail(String email, String token) {
        try {
            email = "lakshithaf20@gmail.com";
            // Create a MIME (Multipurpose Internet Mail Extensions) message (supports attachments)
            SimpleMailMessage message = new SimpleMailMessage();

            String verificationUrl = " http://localhost:8080/reset-password?token=" + token;
            String subject = "Rest Your Password";
            String body = "Click the link to Rest: " + verificationUrl;

            message.setFrom("lakshithakapruka@gmail.com");
            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }




}
