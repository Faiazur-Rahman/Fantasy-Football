package com.brainstation.fantasyfootball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public Integer sendMail(String toMail) {
        SimpleMailMessage message = new SimpleMailMessage();
        Integer OTP = (int) (Math.random() * (9000) + 1000);
        String fromMail = "shahinbeg80@gmail.com";
        String subject = "Mail Verification from Fantasy Football!";
        String text = "Your OTP code is: " + OTP + "\r\n Please verify by this OTP code. Don't share the code anyone!";
        message.setFrom(fromMail);
        message.setTo(toMail);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        return OTP;

    }
}
