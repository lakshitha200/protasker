package com.protasker.protasker_backend.controller.NotificationController;

import com.protasker.protasker_backend.service.NotificationService.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @RequestMapping("/send-email")
    public String sendEmail() {
        return emailService.sendEmail();
    }

    @RequestMapping("/send-email-with-attachment")
    public String sendEmailWithAttachment() {
       return emailService.sendEmailWithAttachment();
    }

    @RequestMapping("/send-html-email")
    public String sendHtmlEmail() {
        return emailService.sendHtmlEmail();
    }

//    @RequestMapping("/send-html-email")
//    public String sendHtmlEmail() {
//        return emailService.sendHtmlEmail();
//    }
}