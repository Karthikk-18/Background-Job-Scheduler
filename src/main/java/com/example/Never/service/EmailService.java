package com.example.Never.service;

import com.example.Never.dto.EmailPayload;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailPayload payload){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(payload.getTo());
        message.setSubject(payload.getSubject());
        message.setText(payload.getBody());
        System.out.println("🚀 Attempting to send REAL email to: " + payload.getTo());
        System.out.println("MAIL USER = " + System.getenv("EMAIL_USERNAME"));
        mailSender.send(message);
    }
}
